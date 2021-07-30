package pe.com.pacifico.kuntur.business.threads;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.FileCopyUtils;
import pe.com.pacifico.kuntur.exception.ValidateException;
import pe.com.pacifico.kuntur.model.CellData;
import pe.com.pacifico.kuntur.model.RequestFileRead;
import pe.com.pacifico.kuntur.repository.MovDriverObjetoJpaRepository;
import pe.com.pacifico.kuntur.util.Constant;
import pe.com.pacifico.kuntur.util.ExcelUtil;
import pe.com.pacifico.kuntur.util.FileUtil;
import pe.com.pacifico.kuntur.util.StringUtil;


/**
 * <b>Class</b>: HiloPrueba <br/>
 * <b>Copyright</b>: 2021 Pacifico Seguros - La Chakra <br/>.
 *
 * @author 2021  Management Solutions <br/>
 * <u>Service Provider</u>: Soliciones Digitales <br/>
 * <u>Developed by</u>: Management Solutions <br/>
 * <u>Changes:</u><br/>
 * <ul>
 *   <li>
 *     May 6, 2021 Creación de Clase.
 *   </li>
 * </ul>
 */
@AllArgsConstructor
@Builder
@ToString
@Slf4j
public class CargarDriverObjetoHilo extends Thread {

  public static Thread hilo = null;
  public static boolean enEjecucion = false;
  public static boolean terminoEjecucion = false;
  public static List<String> errores;
  public static int periodo;
  public static int repartoTipo;
  public static RequestFileRead requestFileRead;
  public static MovDriverObjetoJpaRepository movDriverObjetoJpaRepository;
  private static String dateFormatStringAllowed;
  private static String dateFormatAllowed;

  /**
   * Ejecucion del hilo.
   */
  public void run() {
    List<String> errores = new ArrayList<>();
    if (requestFileRead.getEncodeByte() != null) {
      log.info("Generar Archivo tmp");
      String fileName = StringUtil.convertCurrentDateToText();
      String clearName = StringUtil.removeDiacritics(fileName);
      String filePath = Constant.TMP_PATH + File.separator + clearName;
      log.info(String.format("Ruta archivo tmp: %s", filePath));
      File file = new File(filePath);
      log.info("Decodificando cadena Base64");
      byte[] bytes = Base64.getDecoder().decode(requestFileRead.getEncodeByte());
      InputStream is = new ByteArrayInputStream(bytes);
      List<List<CellData>> listExcelBodyS1;
      List<List<CellData>> listExcelBodyS2;
      try {
        String mimeType = URLConnection.guessContentTypeFromStream(is);
        log.info("Crear archivo");
        FileCopyUtils.copy(bytes, file);

        listExcelBodyS1 = ExcelUtil.getRowList_v2(filePath, dateFormatAllowed, dateFormatStringAllowed, 0);
        listExcelBodyS2 = ExcelUtil.getRowList_v2(filePath, dateFormatAllowed, dateFormatStringAllowed, 1);

        System.out.println("Size xl 1: " + listExcelBodyS1.size());
        System.out.println("Size xl 2: " + listExcelBodyS2.size());

        errores = movDriverObjetoJpaRepository.saveExcelToBD_MaDriver(listExcelBodyS1, repartoTipo, periodo);
        List<String> aux = movDriverObjetoJpaRepository.saveExcelToBD_MovDriverObjeto(listExcelBodyS2, repartoTipo, periodo);
        if (errores != null)
        {
          errores.addAll(aux);
        } else {
          errores = aux;
        }
        log.info("Leer archivo");
        log.info("Borrar archivo");
        FileUtil.removeTempFile(clearName);
        log.info("Fin de proceso");
      } catch (ValidateException | IOException ex) {
        log.error("Error genérico al crear archivo temporal", ex);
        errores.add("Error genérico al crear archivo temporal: " + ex.getMessage());
      } catch (Exception ex) {
        log.error("Error", ex);
        errores.add("Error: " + ex.getMessage());
      }
    }
    CargarDriverObjetoHilo.enEjecucion = false;
    CargarDriverObjetoHilo.errores = errores;
    CargarDriverObjetoHilo.terminoEjecucion = true;
  }

  /**
   * Inicio del hilo.
   */
  public static void begin(int repartoTipo, int periodo, RequestFileRead requestFileRead,
                           MovDriverObjetoJpaRepository movDriverObjetoJpaRepository, String dfsa, String dfa) {
    CargarDriverObjetoHilo.repartoTipo = repartoTipo;
    CargarDriverObjetoHilo.periodo = periodo;
    CargarDriverObjetoHilo.requestFileRead = requestFileRead;
    CargarDriverObjetoHilo.movDriverObjetoJpaRepository = movDriverObjetoJpaRepository;
    CargarDriverObjetoHilo.dateFormatAllowed = dfa;
    CargarDriverObjetoHilo.dateFormatStringAllowed = dfsa;

    Thread hilo = new CargarDriverObjetoHilo();
    enEjecucion = true;
    hilo.start();
  }

  /**
   * Limpiar el hilo.
   */
  public static void limpiarHilo() {
    hilo = null;
    enEjecucion = false;
    terminoEjecucion = false;
    errores = null;
  }

}
