package com.hcmsouza.cursomc;

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
import com.hcmsouza.cursomc.domain.Produto;
import com.hcmsouza.cursomc.domain.enums.TipoCliente;
import com.hcmsouza.cursomc.repositories.CategoriaRepository;
import com.hcmsouza.cursomc.repositories.CidadeRepository;
import com.hcmsouza.cursomc.repositories.ClienteRepository;
import com.hcmsouza.cursomc.repositories.EnderecoRepository;
import com.hcmsouza.cursomc.repositories.EstadoRepository;
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
		
	}

	
	
}
