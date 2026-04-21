# CLAUDE.md — Instruções de Trabalho

Este arquivo define como Claude deve se comportar em todas as sessões deste projeto.

---

## Modo de Ensino

Em **todos os dias do plano de estudos**, ao adicionar ou modificar código:

1. **Explique cada arquivo criado ou alterado** — o que ele é, qual sua responsabilidade no projeto
2. **Explique cada bloco de código relevante** — o que faz, como funciona
3. **Explique o porquê de cada escolha** — por que essa anotação, por que essa estrutura, por que essa dependência, quais as alternativas e por que não foram escolhidas
4. **Explique os conceitos envolvidos** — se usar um padrão (Factory Method, Singleton, etc.), nomear e explicar
5. **Mostre o fluxo completo** quando relevante — como os arquivos se conectam entre si

## Ao Final de Cada Dia

- Fazer commit e push para o GitHub com mensagem descritiva
- Atualizar o `DIA{N}_EXPLICACAO.txt` com tudo que foi visto (código + conceitos + dúvidas respondidas)

## Estilo de Resposta

- Direto ao ponto — sem enrolação
- Comentários no código explicando o raciocínio
- Mostrar o "antes e depois" quando modificar código existente
- Quando houver alternativas (ex: Optional vs exceção), explicar a troca

## Contexto do Projeto

- **Objetivo:** Preparar para entrevista de vaga sênior (Java + Cloud + LLMs)
- **Projeto:** Corporate Travel Assistant API
- **Stack:** Java 21 + Spring Boot + Docker + Kubernetes + AWS + LLM (Anthropic)
- **Repositório:** https://github.com/roger-almeida/travel-ai-api

## Plano de Estudos

| Semana | Dias | Tema |
|--------|------|------|
| 1 | 1-2 | Java OOP + Spring Boot REST API ✅ |
| 1 | 3-4 | Docker |
| 1 | 5-7 | API dockerizada (projeto) |
| 2 | 1-2 | Kubernetes |
| 2 | 3-4 | AWS (DynamoDB, EC2, EKS) |
| 2 | 5-7 | API no K8s + DynamoDB |
| 3 | 1-2 | APIs de LLM + Prompt Engineering |
| 3 | 3-4 | RAG — Retrieval-Augmented Generation |
| 3 | 5-7 | Endpoint de IA |
| 4 | 1-2 | CI/CD com GitHub Actions |
| 4 | 3-4 | Observabilidade |
| 4 | 5-7 | Revisão + Simulação de Entrevista |

## Para Retomar em Nova Sessão

Cole este prompt:
> "Estou seguindo o plano de estudos do repositório travel-ai-api. Estou na Semana X, Dia Y. Leia o CLAUDE.md e me ajude a continuar."
