package estrategiasDeBusca.cega;

import java.util.Collections;
import java.util.List;

import espacoDeEstados.Estado;

/**
 * Esta classe implementa uma estratégia de busca cega conhecida como "Busca em
 * Profundidade Limitada Iterativa", que é característica por explorar o espaço
 * de busca aumentando a profundidade de maneira iterativa até encontrar a solução.
 * 
 * @author Leandro C. Fernandes
 *
 */
public class BuscaEmProfundidadeLimitadaIterativa extends BuscaEmProfundidade {
	
	private int limiteMaximo;

	/**
	 * Construtor padrão.
	 */
	public BuscaEmProfundidadeLimitadaIterativa() {
		this(null, null, 10);
	}
	
	/**
	 * Cria uma nova instância de Busca em Profundidade Limitada Iterativa,
	 * definindo os estados inicial e objetivo para o processo, além do limite máximo de profundidade.
	 * 
	 * @param estadoInicial estado inicial de busca.
	 * @param estadoMeta estado que contém os objetivos da busca.
	 * @param limiteMaximo limite máximo de profundidade (irá aumentar iterativamente).
	 */
	public BuscaEmProfundidadeLimitadaIterativa(Estado<?> estadoInicial, Estado<?> estadoMeta, int limiteMaximo) {
		super(estadoInicial, estadoMeta);
		super.nomeDaEstrategia = "Busca em Profundidade Limitada Iterativa (até " + limiteMaximo + " níveis)";
		this.limiteMaximo = limiteMaximo;
	}
	
	/**
	 * Retorna o limite máximo de profundidade que será incrementado iterativamente.
	 */
	public int getLimiteMaximo() {
		return limiteMaximo;
	}

	/**
	 * Define o limite máximo de profundidade que será incrementado iterativamente.
	 */
	public void setLimiteMaximo(int limiteMaximo) {
		this.limiteMaximo = limiteMaximo;
	}

	/**
	 * Implementa efetivamente a estratégia de busca iterativa em profundidade limitada.
	 * A profundidade máxima é aumentada em cada iteração até a solução ser encontrada.
	 */
	@Override
	public void buscar() {
		Estado<?> eCorrente = eInicial;
		boolean encontrouSolucao = false;

		// A busca será iterativa, aumentando a profundidade
		int profundidadeAtual = 0;
		while (!encontrouSolucao && profundidadeAtual <= limiteMaximo) {
			// Reinicia as estruturas de dados para cada nova profundidade
			eAbertos.clear();
			eFechados.clear();
			eCorrente = eInicial;
			eCorrente.setNivel(0);  // Garante que o estado inicial tenha nível 0
			
			while (eCorrente != null && !eCorrente.equals(eObjetivo)) {
				if (eCorrente.getNivel() < profundidadeAtual) {
					// Para cada estado que está no nível permitido, expande seus sucessores
					for (Estado<?> estado : (List<Estado<?>>) eCorrente.getSucessores()) {
						eAbertos.push(estado);
					}
				}
				// A busca em profundidade continua explorando os sucessores do estado corrente
				eCorrente = eAbertos.pop();
			}

			// Verifica se encontrou a solução na profundidade atual
			if (eCorrente != null) {
				// Se encontrou, construa o caminho até o objetivo
				caminho.add(eCorrente);
				while (eCorrente.getAncestral() != null) {
					eCorrente = eCorrente.getAncestral();
					caminho.add(eCorrente);
				}
				Collections.reverse(caminho);
				encontrouSolucao = true;
			} else {
				// Caso não encontre, incrementa o limite de profundidade
				profundidadeAtual++;
			}
		}
	}
}

