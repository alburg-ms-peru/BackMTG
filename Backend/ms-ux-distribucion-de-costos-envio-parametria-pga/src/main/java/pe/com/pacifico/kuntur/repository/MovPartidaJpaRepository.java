package pe.com.pacifico.kuntur.repository;

import java.util.List;
import pe.com.pacifico.kuntur.expose.request.MovPartidaRequest;
import pe.com.pacifico.kuntur.model.CellData;
import pe.com.pacifico.kuntur.model.MovPartida;

/**
 * <b>Class</b>: MovPartidaJpaRepository <br/>
 * <b>Copyright</b>: 2021 Pacifico Seguros - La Chakra <br/>.
 *
 * @author 2021  Management Solutions <br/>
 * <u>Service Provider</u>: Soluciones Digitales <br/>
 * <u>Developed by</u>: prueba <br/>
 * <u>Changes:</u><br/>
 * <ul>
 *   <li>
 *     April 12, 2021 Creación de Clase.
 *   </li>
 * </ul>
 */
public interface MovPartidaJpaRepository {

  List<MovPartida> findAll(int repartoTipo, int periodo);

  int save(MovPartidaRequest partida);

  boolean delete(int repartoTipo, int periodo, String codigo);

  List<String> findAllCodPartida(int repartoTipo);

  List<String> saveExcelToBD(List<List<CellData>> excel, int repartoTipo, int periodo);

}
