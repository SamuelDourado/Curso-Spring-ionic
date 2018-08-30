package com.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.cursomc.domain.Categoria;
import com.cursomc.domain.Cidade;
import com.cursomc.domain.Cliente;
import com.cursomc.domain.Endereco;
import com.cursomc.domain.Estado;
import com.cursomc.domain.ItemPedido;
import com.cursomc.domain.Pagamento;
import com.cursomc.domain.PagamentoComBoleto;
import com.cursomc.domain.PagamentoComCartao;
import com.cursomc.domain.Pedido;
import com.cursomc.domain.Produto;
import com.cursomc.domain.enums.EstadoPagamento;
import com.cursomc.domain.enums.TipoCliente;
import com.cursomc.ropositories.CategoriaRepository;
import com.cursomc.ropositories.CidadeRepository;
import com.cursomc.ropositories.ClienteRepository;
import com.cursomc.ropositories.EnderecoRepository;
import com.cursomc.ropositories.EstadoRepository;
import com.cursomc.ropositories.ItemPedidoRepository;
import com.cursomc.ropositories.PagamentoRepository;
import com.cursomc.ropositories.PedidoRepository;
import com.cursomc.ropositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{
	@Autowired
	private CategoriaRepository RCategoria;
	@Autowired
	private ProdutoRepository RProduto;
	@Autowired
	private EstadoRepository REstado;
	@Autowired
	private CidadeRepository RCidade;
	@Autowired
	private ClienteRepository RCliente;
	@Autowired
	private EnderecoRepository REndereco;
	@Autowired
	private PedidoRepository RPedido;
	@Autowired
	private PagamentoRepository RPagamento;
	@Autowired
	private ItemPedidoRepository RItemPedido;

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		Categoria cat3 = new Categoria();
		
		Produto prod1 = new Produto(null, "Computador", 2000.00);
		Produto prod2 = new Produto(null, "Impressora", 800.00);
		Produto prod3 = new Produto(null, "Mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(prod1,prod2));
		cat2.getProdutos().addAll(Arrays.asList(prod3));
		
		prod1.getCategorias().addAll(Arrays.asList(cat1));
		prod2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		prod3.getCategorias().addAll(Arrays.asList(cat1));
		
		Estado est1 = new Estado(null, "Minas Gerais");
 		Estado est2 = new Estado(null, "São Paulo");
 		
 		Cidade c1 = new Cidade(null, "Uberlândia", est1);
 		Cidade c2 = new Cidade(null, "São Paulo", est2);
 		Cidade c3 = new Cidade(null, "Campinas", est2);
 		
 		est1.getCidades().addAll(Arrays.asList(c1));
 		est2.getCidades().addAll(Arrays.asList(c2, c3));
 		
 		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);
 		
 		cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));
 		
 		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardim", "38220834", cli1, c1);
 		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, c2);
 		
 		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
 		
 		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
 		
 		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
 		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);
 		
 		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
 		ped1.setPagamento(pagto1);
 		
 		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
 		ped2.setPagamento(pagto2);
 		
 		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
 		
 		ItemPedido ip1 = new ItemPedido(ped1, prod1, 0.00, 1, 2000.00);
 		ItemPedido ip2 = new ItemPedido(ped1, prod3, 0.00, 2, 80.00);
 		ItemPedido ip3 = new ItemPedido(ped2, prod2, 100.00, 1, 800.00);
 		
 		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
 		ped2.getItens().addAll(Arrays.asList(ip3));
 		
 		prod1.getItens().addAll(Arrays.asList(ip1));
 		prod2.getItens().addAll(Arrays.asList(ip3));
 		prod3.getItens().addAll(Arrays.asList(ip2));
		
		this.RCategoria.saveAll(Arrays.asList(cat1, cat2));
		this.RProduto.saveAll(Arrays.asList(prod1,prod2,prod3));
		this.REstado.saveAll(Arrays.asList(est1, est2));
		this.RCidade.saveAll(Arrays.asList(c1, c2, c3));
		this.RCliente.saveAll(Arrays.asList(cli1));
		this.REndereco.saveAll(Arrays.asList(e1, e2));
		this.RPedido.saveAll(Arrays.asList(ped1, ped2));
		this.RPagamento.saveAll(Arrays.asList(pagto1, pagto2));
		this.RItemPedido.saveAll(Arrays.asList(ip1, ip2, ip3));
		
	}
}
