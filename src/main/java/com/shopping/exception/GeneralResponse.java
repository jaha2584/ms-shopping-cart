package com.shopping.exception;

//import io.swagger.annotations.ApiModelProperty;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GeneralResponse {
	
    private Integer numeroLinea;
    
 //   @ApiModelProperty(value = "Tipo mensaje de respuesta. Opciones: |OK|Éxito, |EX|Error en la ejecución.", example = "OK")
    private String tipoMensajeRespuesta;

 //   @ApiModelProperty(value = "Tipo de severidad", example = "1")
    private String tipoSeveridad;

 //   @ApiModelProperty(value = "Código de mensaje respuesta.", example = "0000")
    private String codigoMensajeRespuesta;

 //   @ApiModelProperty(value = "Descripción de mensaje respuesta.", example = "La transacción se realizó satisfactoriamente.")
    private String descripcionMensajeRespuesta;

 //   @ApiModelProperty(value = "Descripción técnica del mensaje respuesta.", example = "La transacción se realizó satisfactoriamente.")
    private String detalleTecnicoRespuesta;

}
