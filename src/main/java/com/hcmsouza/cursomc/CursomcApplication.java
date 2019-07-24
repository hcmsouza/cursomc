package com.hcmsouza.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.hcmsouza.cursomc.domain.Categoria;
import com.hcmsouza.cursomc.domain.Cidade;
import com.hcmsouza.cursomc.domain.Cliente;
import com.hcmsouza.cursomc.domain.Endereco;
import com.hcmsouza.cursomc.domain.Estado;
import com.hcmsouza.cursomc.domain.ItemPedido;
import com.hcmsouza.cursomc.domain.Pagamento;
import com.hcmsouza.cursomc.domain.PagamentoComBoleto;
import com.hcmsouza.cursomc.domain.PagamentoComCartao;
import com.hcmsouza.cursomc.domain.Pedido;
import com.hcmsouza.cursomc.domain.Produto;
import com.hcmsouza.cursomc.domain.enums.EstadoPagamento;
import com.hcmsouza.cursomc.domain.enums.TipoCliente;
import com.hcmsouza.cursomc.repositories.CategoriaRepository;
import com.hcmsouza.cursomc.repositories.CidadeRepository;
import com.hcmsouza.cursomc.repositories.ClienteRepository;
import com.hcmsouza.cursomc.repositories.EnderecoRepository;
import com.hcmsouza.cursomc.repositories.EstadoRepository;
import com.hcmsouza.cursomc.repositories.ItemPedidoRepository;
import com.hcmsouza.cursomc.repositories.PagamentoRepository;
import com.hcmsouza.cursomc.repositories.PedidoRepository;
import com.hcmsouza.cursomc.repositories.ProdutoRespoitory;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{

	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRespoitory produtoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");

		
		Produto p1 = new Produto(null, "Computador", 2500.00);
		Produto p2 = new Produto(null, "Impressora", 100.00);
		Produto p3 = new Produto(null, "Mouse", 30.00);

		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepository.save(Arrays.asList(cat1,cat2));
		produtoRepository.save(Arrays.asList(p1, p2, p3));		
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlandia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c2));
		est2.getCidades().addAll(Arrays.asList(c2,c3));
		
		estadoRepository.save(Arrays.asList(est1, est2));
		cidadeRepository.save(Arrays.asList(c1,c2,c3));
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "3215498751", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("3216555","23165547"));
		
		Endereco e1 = new Endereco(null, "Rua Flores", "123", "esquina", "jd luz", "01324-654", c1, cli1);
		Endereco e2 = new Endereco(null, "Rua matos", "198", "sala 8", "centro", "06544-654", c2, cli1);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
		
		clienteRepository.save(Arrays.asList(cli1));
		enderecoRepository.save(Arrays.asList(e1, e2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/07/2017 14:35"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("15/01/2018 11:30"), cli1, e2);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		pedidoRepository.save(Arrays.asList(ped1, ped2));
		pagamentoRepository.save(Arrays.asList(pagto1, pagto2));
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 0.00, 1, 80.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		itemPedidoRepository.save(Arrays.asList(ip1,ip2,ip3));
		pedidoRepository.save(Arrays.asList(ped1, ped2));
		produtoRepository.save(Arrays.asList(p1, p2, p3));	
		
		
	}

	
	
}
