package br.edu.cs.poo.ac.seguro.testes;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import br.edu.cs.poo.ac.seguro.daos.ApoliceDAO;
import br.edu.cs.poo.ac.seguro.entidades.Apolice;
import br.edu.cs.poo.ac.seguro.entidades.CategoriaVeiculo;
import br.edu.cs.poo.ac.seguro.entidades.Veiculo;

public class TesteApoliceDAO extends TesteDAO {
    private ApoliceDAO dao = new ApoliceDAO();
    protected Class getClasse() {
        return Apolice.class;
    }

    private Apolice criarApolice(String numero, BigDecimal franquia, BigDecimal premio, BigDecimal maximo) {
        Veiculo veiculo = new Veiculo("ABC1234", 2020, null, null, CategoriaVeiculo.BASICO);
        Apolice apolice = new Apolice(veiculo, franquia, premio, maximo);
        apolice.setNumero(numero);
        return apolice;
    }

    @Test
    public void teste01() {
        String numero = "00000000";
        cadastro.incluir(criarApolice(numero, new BigDecimal("500.00"), new BigDecimal("1000.00"),
                new BigDecimal("50000.00")), numero);
        Apolice ap = dao.buscar(numero);
        Assertions.assertNotNull(ap);
    }
    @Test
    public void teste02() {
        String numero = "10000000";
        cadastro.incluir(criarApolice(numero, new BigDecimal("501.00"), new BigDecimal("1001.00"),
                new BigDecimal("50001.00")), numero);
        Apolice ap = dao.buscar("11000000");
        Assertions.assertNull(ap);
    }
    @Test
    public void teste03() {
        String numero = "20000000";
        cadastro.incluir(criarApolice(numero, new BigDecimal("502.00"), new BigDecimal("1002.00"),
                new BigDecimal("50002.00")), numero);
        boolean ret = dao.excluir(numero);
        Assertions.assertTrue(ret);
    }
    @Test
    public void teste04() {
        String numero = "30000000";
        cadastro.incluir(criarApolice(numero, new BigDecimal("503.00"), new BigDecimal("1003.00"),
                new BigDecimal("50003.00")), numero);
        boolean ret = dao.excluir("31000000");
        Assertions.assertFalse(ret);
    }
    @Test
    public void teste05() {
        String numero = "40000000";
        boolean ret = dao.incluir(criarApolice(numero, new BigDecimal("504.00"), new BigDecimal("1004.00"),
                new BigDecimal("50004.00")));
        Assertions.assertTrue(ret);
        Apolice ap = dao.buscar(numero);
        Assertions.assertNotNull(ap);
    }

    @Test
    public void teste06() {
        String numero = "50000000";
        Apolice ap = criarApolice(numero, new BigDecimal("505.00"), new BigDecimal("1005.00"),
                new BigDecimal("50005.00"));
        cadastro.incluir(ap, numero);
        boolean ret = dao.incluir(ap);
        Assertions.assertFalse(ret);
    }
    @Test
    public void teste07() {
        String numero = "60000000";
        boolean ret = dao.alterar(criarApolice(numero, new BigDecimal("506.00"), new BigDecimal("1006.00"),
                new BigDecimal("50006.00")));
        Assertions.assertFalse(ret);
        Apolice ap = dao.buscar(numero);
        Assertions.assertNull(ap);
    }

    @Test
    public void teste08() {
        String numero = "70000000";
        Apolice ap = criarApolice(numero, new BigDecimal("507.00"), new BigDecimal("1007.00"),
                new BigDecimal("50007.00"));
        cadastro.incluir(ap, numero);
        ap = criarApolice(numero, new BigDecimal("508.00"), new BigDecimal("1008.00"),
                new BigDecimal("50008.00"));
        boolean ret = dao.alterar(ap);
        Assertions.assertTrue(ret);
    }
}
