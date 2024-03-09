package fabrica;

/**
 * @author caio.arthur
 */
public class Cliente {
    private String requisicaoGrade;
    private boolean possuiContratoEmpresa;

    public Cliente(String requisicaoGrade, boolean possuiContratoEmpresa) {
        this.requisicaoGrade = requisicaoGrade;
        this.possuiContratoEmpresa = possuiContratoEmpresa;
    }

    public boolean possuiContratoEmpresa() {
        return possuiContratoEmpresa;
    }

    public String getRequisicaoGrade() {
        return requisicaoGrade;
    }
}
