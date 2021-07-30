package pe.com.pacifico.kuntur.repository;

import java.util.List;
import pe.com.pacifico.kuntur.model.CellData;
import pe.com.pacifico.kuntur.model.Exactus;

/**
 * <b>Class</b>: DetalleGastoServiceJpaRepository <br/>
 * <b>Copyright</b>: 2021 Pacifico Seguros - La Chakra <br/>.
 *
 * @author 2021  Management Solutions <br/>
 * <u>Service Provider</u>: Soluciones Digitales <br/>
 * <u>Developed by</u>: prueba <br/>
 * <u>Changes:</u><br/>
 * <ul>
 *   <li>
 *     March 31, 2021 Creación de Clase.
 *   </li>
 * </ul>
 */
public interface ExactusJpaRepository {

  List<Exactus> findAll(int repartoTipo,int periodo);

  List<String> saveExcelToBd(List<List<CellData>> excel,int repartoTipo, int periodo);

  List<String> findAllCodCuentaContable(int repartoTipo, int periodo);

  List<String> findAllCodPartida(int repartoTipo, int periodo);

  List<String> findAllCodCentro(int repartoTipo, int periodo);


}
