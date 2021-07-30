package pe.com.pacifico.kuntur.expose.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * <b>Class</b>: MovSubcanalResponse <br/>
 * <b>Copyright</b>: 2021 Pacifico Seguros - La Chakra <br/>.
 *
 * @author 2021  Management Solutions <br/>
 * <u>Service Provider</u>: Soluciones Digitales <br/>
 * <u>Developed by</u>: Management Solutions <br/>
 * <u>Changes:</u><br/>
 * <ul>
 *   <li>
 *     April 16, 2021 Creación de Clase.
 *   </li>
 * </ul>
 */
@ApiModel(description = "MovSubcanalResponse model")
@AllArgsConstructor
@Builder
@Getter
@NoArgsConstructor
@Setter
@ToString
public class MovSubcanalResponse implements Serializable {

  private static final long serialVersionUID = 1L;

  @NotNull
  @ApiModelProperty(example = "ABC001")
  private String codSubcanal;

  @ApiModelProperty(example = "ABC")
  private String nombre;

  @NotNull
  @ApiModelProperty(example = "202101")
  private int periodo;

  @ApiModelProperty(example = "1")
  private int repartoTipo;

  @ApiModelProperty(example = "2021-03-31")
  private Date fechaCreacion;

  @ApiModelProperty(example = "2021-03-31")
  private Date fechaActualizacion;

  @ApiModelProperty(example = "ABC")
  private String codCanal;

  @ApiModelProperty(example = "ABC")
  private String nombreCanal;

}
