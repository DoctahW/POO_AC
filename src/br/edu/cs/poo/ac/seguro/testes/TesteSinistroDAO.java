package br.edu.cs.poo.ac.seguro.testes;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import br.edu.cs.poo.ac.seguro.daos.SinistroDAO;
import br.edu.cs.poo.ac.seguro.entidades.CategoriaVeiculo;
import br.edu.cs.poo.ac.seguro.entidades.Sinistro;
import br.edu.cs.poo.ac.seguro.entidades.TipoSinistro;
import br.edu.cs.poo.ac.seguro.entidades.Veiculo;

public class TesteSinistroDAO extends TesteDAO {
    private SinistroDAO dao = new SinistroDAO();
    protected Class getClasse() {
        return Sinistro.class;
    }

    private Sinistro criarSinistro(String numero, String usuario, BigDecimal valor, TipoSinistro tipo) {
        Veiculo veiculo = new Veiculo("ABC1234", 2020, null, null, CategoriaVeiculo.BASICO);
        Sinistro sinistro = new Sinistro(numero, veiculo, LocalDateTime.now(), LocalDateTime.now(),
                usuario, valor, tipo);
        return sinistro;
    }

    @Test
    public void teste01() {
        String numero = "00000000";
        cadastro.incluir(criarSinistro(numero, "USUARIO1", new BigDecimal("1000.00"), TipoSinistro.COLISAO), numero);
        Sinistro sin = dao.buscar(numero);
        Assertions.assertNotNull(sin);
    }
    @Test
    public void teste02() {
        String numero = "10000000";
        cadastro.incluir(criarSinistro(numero, "USUARIO2", new BigDecimal("1001.00"), TipoSinistro.COLISAO), numero);
        Sinistro sin = dao.buscar("11000000");
        Assertions.assertNull(sin);
    }
    @Test
    public void teste03() {
        String numero = "20000000";
        cadastro.incluir(criarSinistro(numero, "USUARIO3", new BigDecimal("1002.00"), TipoSinistro.INCENDIO), numero);
        boolean ret = dao.excluir(numero);
        Assertions.assertTrue(ret);
    }
    @Test
    public void teste04() {
        String numero = "30000000";
        cadastro.incluir(criarSinistro(numero, "USUARIO4", new BigDecimal("1003.00"), TipoSinistro.FURTO), numero);
        boolean ret = dao.excluir("31000000");
        Assertions.assertFalse(ret);
    }
    @Test
    public void teste05() {
        String numero = "40000000";
        boolean ret = dao.incluir(criarSinistro(numero, "USUARIO5", new BigDecimal("1004.00"), TipoSinistro.ENCHENTE));
        Assertions.assertTrue(ret);
        Sinistro sin = dao.buscar(numero);
        Assertions.assertNotNull(sin);
    }

    @Test
    public void teste06() {
        String numero = "50000000";
        Sinistro sin = criarSinistro(numero, "USUARIO6", new BigDecimal("1005.00"), TipoSinistro.DEPREDACAO);
        cadastro.incluir(sin, numero);
        boolean ret = dao.incluir(sin);
        Assertions.assertFalse(ret);
    }
    @Test
    public void teste07() {
        String numero = "60000000";
        boolean ret = dao.alterar(criarSinistro(numero, "USUARIO7", new BigDecimal("1006.00"), TipoSinistro.COLISAO));
        Assertions.assertFalse(ret);
        Sinistro sin = dao.buscar(numero);
        Assertions.assertNull(sin);
    }

    @Test
    public void teste08() {
        String numero = "70000000";
        Sinistro sin = criarSinistro(numero, "USUARIO8", new BigDecimal("1007.00"), TipoSinistro.COLISAO);
        cadastro.incluir(sin, numero);
        sin = criarSinistro(numero, "USUARIO9", new BigDecimal("1008.00"), TipoSinistro.FURTO);
        boolean ret = dao.alterar(sin);
        Assertions.assertTrue(ret);
    }
}
