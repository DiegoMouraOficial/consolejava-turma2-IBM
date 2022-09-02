package com.br.diego.console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.br.diego.console.models.Aluno;

//import org.springframework.boot.SpringApplication;

@SpringBootApplication
public class LogicaApplication {

	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private static List<Aluno> alunos = new ArrayList<>();

	/**
	 * @param args
	 * @throws IOException
	 * @throws NumberFormatException
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws IOException, NumberFormatException, InterruptedException {

		while (true) {
			limparTela();
			System.out.println("=====[Cadastro dos Alunos]=====");

			// Mostrar opcoes para o usuario
			System.out.println("Qual opção você deseja?");
			System.out.println("1 - Cadastrar aluno");
			System.out.println("2 - Mostrar aluno");
			System.out.println("3 - Sair");

			// Le a opcao digitada pelo usuario
			int opcao = 0;
			try {
				Integer.parseInt(reader.readLine());
			} catch (Exception e) {
			}

			var sair = false;
			switch (opcao) {
				case 1:
					cadastroAluno();
					break;
				case 2:
					mostrarAluno();
					break;
				case 3:
					sair = true;
					break;
				default:
					opcaoInvalida();
					break;
			}

			if (sair)
				break;
		}

		// SpringApplication.run(LogicaApplication.class, args);

	}

	private static void limparTela() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

	private static void opcaoInvalida() throws IOException, NumberFormatException, InterruptedException {
		mensagem("Opção inválida");

	}

	private static void capturaNotasAluno(Aluno aluno) throws NumberFormatException, IOException, InterruptedException {
		System.out.println("Digite o nota do(a)" + aluno.getNotas());
		if (aluno.getNotas() == null)
			aluno.setNotas(new ArrayList<Float>());

		try {
			aluno.getNotas().add(Float.parseFloat(reader.readLine()));
		} catch (Exception e) {
			mensagem("Nota inválida");
			capturaNotasAluno(aluno);
		}

		try {
			System.out.println("Digite 1 para cadastrar mais notas ou 0 para finalizar o cadastro");
			int opcao = Integer.parseInt(reader.readLine());
			if (opcao == 1)
				capturaNotasAluno(aluno);
			else {
				mensagem("Aluno cadastrado com sucesso!");

			}
		} catch (Exception e) {
			mensagem("Opção inválida, iniciando novo cadastro de notas");
			capturaNotasAluno(aluno);
		}

	}

	private static void mensagem(String string) throws InterruptedException {
		limparTela();
		System.out.println(string);
		espera(2);
		limparTela();
	}

	private static void espera(int secconds) throws InterruptedException {
		Thread.sleep(secconds * 1000);

	}

	private static void mostrarAluno() throws InterruptedException {
		if (alunos.size() == 0) {
			mensagem("Nenhum Aluno cadastrado");
			return;
		}
		System.out.println("=====[Relatório de alunos]=====");
		for (Aluno aluno : alunos) {
			System.out.println("Nome:" + aluno.getNome());
			String notas = "";
			for (float nota : aluno.getNotas()) {
				notas += nota + ",";
			}
			System.out.println("Notas:" + notas);
			System.out.println("Média:" + aluno.media());
			System.out.println("Situação:" + aluno.situacao());
			System.out.println("--------------------------------");

		}

		espera(2);
		limparTela();

	}

	private static void cadastroAluno() throws NumberFormatException, IOException, InterruptedException {
		var aluno = new Aluno();
		System.out.println("Digite o nome do aluno");
		aluno.setNome(reader.readLine());

		capturaNotasAluno(aluno);

	}
}
