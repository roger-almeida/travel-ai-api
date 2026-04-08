# Travel AI API — Plano de Estudos Intensivo

**Objetivo:** Preparar para entrevista de vaga sênior (Java + Cloud + LLMs) em 2–4 semanas.

**Projeto âncora:** API REST de Corporate Travel Assistant com IA integrada.
**Stack:** Java 21 + Spring Boot + Docker + Kubernetes + AWS + LLM (Anthropic/OpenAI)

---

## Progresso Geral

- [ ] Semana 1 — Java Intermediário + Docker
- [ ] Semana 2 — Kubernetes + AWS
- [ ] Semana 3 — LLM + RAG
- [ ] Semana 4 — DevOps + Polimento + Simulação de Entrevista

---

## Semana 1 — Java Intermediário + Docker

**Meta:** Ter uma API Java com 3+ endpoints rodando em container Docker.

### Dia 1–2: Java OOP + Spring Boot REST API
- [ ] OOP: classes, herança, interfaces, encapsulamento
- [ ] Injeção de dependência (`@Service`, `@Repository`, `@RestController`)
- [ ] Criar endpoints: `GET /trips`, `POST /trips`, `GET /trips/{id}`
- [ ] Entender DTOs e camadas (Controller → Service → Repository)

### Dia 3–4: Docker
- [ ] O que é container vs VM
- [ ] Escrever um `Dockerfile` para a API Java
- [ ] Usar `docker build` e `docker run`
- [ ] Escrever `docker-compose.yml` com API + banco local

### Dia 5–7: Projeto — API dockerizada
- [ ] API com 3 endpoints funcionando
- [ ] Dockerfile e docker-compose prontos
- [ ] README com instruções de como rodar
- [ ] Push no GitHub

**Checkpoint:** Conseguir rodar `docker-compose up` e chamar a API via Postman/curl.

---

## Semana 2 — Kubernetes + AWS

**Meta:** API rodando em Kubernetes local (minikube) + DynamoDB como banco de dados.

### Dia 1–2: Kubernetes
- [ ] Conceitos: Pod, Deployment, Service, Namespace
- [ ] Escrever manifests YAML (deployment.yaml, service.yaml)
- [ ] Instalar minikube e fazer deploy da API
- [ ] Entender o que é Helm e charts

### Dia 3–4: AWS
- [ ] Criar conta AWS Free Tier
- [ ] EC2: o que é, como criar uma instância
- [ ] DynamoDB: criar tabela, inserir e buscar itens
- [ ] EKS: o que é e como se relaciona com K8s

### Dia 5–7: Projeto — API no K8s + DynamoDB
- [ ] Substituir banco local pelo DynamoDB
- [ ] Manifests K8s para o projeto
- [ ] Deploy funcionando no minikube
- [ ] Push no GitHub

**Checkpoint:** `kubectl get pods` mostra API rodando; `POST /trips` salva no DynamoDB.

---

## Semana 3 — LLM + RAG

**Meta:** Endpoint `/assistant` que responde perguntas usando LLM + contexto de documentos.

### Dia 1–2: APIs de LLM + Prompt Engineering
- [ ] Criar conta Anthropic (ou OpenAI)
- [ ] Fazer primeira chamada à API via Java
- [ ] Entender: tokens, temperatura, system prompt, user prompt
- [ ] Escrever prompts eficazes para o domínio de viagens corporativas
- [ ] Criar templates de prompt reutilizáveis

### Dia 3–4: RAG — Retrieval-Augmented Generation
- [ ] O que são embeddings e por que usar
- [ ] Como funciona RAG: busca vetorial → contexto → LLM
- [ ] Usar uma lib de vetores simples (ex: pgvector ou in-memory)
- [ ] Indexar documentos de política de viagem

### Dia 5–7: Projeto — Endpoint de IA
- [ ] `POST /assistant` recebe pergunta do usuário
- [ ] Sistema busca documentos relevantes (RAG)
- [ ] LLM responde com contexto embasado
- [ ] Push no GitHub

**Checkpoint:** Perguntar "Qual o limite de diária para hotéis?" e receber resposta baseada em documentos.

---

## Semana 4 — DevOps + Polimento + Entrevista

**Meta:** Projeto completo, pipeline CI/CD, e conseguir explicar cada decisão técnica.

### Dia 1–2: CI/CD com GitHub Actions
- [ ] O que é pipeline de CI/CD
- [ ] Criar `.github/workflows/build.yml`
- [ ] Pipeline: build → test → docker build
- [ ] Entender o que é entrega contínua vs deploy contínuo

### Dia 3–4: Observabilidade
- [ ] O que é observabilidade (logs, métricas, traces)
- [ ] Adicionar logs estruturados na API (JSON logs)
- [ ] Entender o que o Kibana mostra e por quê isso importa
- [ ] Conceito de health checks e readiness probes no K8s

### Dia 5–7: Revisão + Simulação de Entrevista
- [ ] Revisar todo o projeto e garantir que roda do zero
- [ ] Escrever README completo com arquitetura
- [ ] Praticar perguntas técnicas:
  - "Me explica a arquitetura do seu projeto"
  - "Como você garantiria segurança nessa API?"
  - "Como você escalaria isso em produção?"
  - "O que é RAG e quando usar?"
  - "Como funciona o Kubernetes?"
- [ ] Simular entrevista com Claude

**Checkpoint:** Conseguir apresentar o projeto em 10 minutos e responder perguntas técnicas com confiança.

---

## Stack do Projeto

```
travel-ai-api/
  src/
    main/java/com/travel/
      controller/     # TripController, AssistantController
      service/        # TripService, AssistantService, RagService
      repository/     # DynamoDbTripRepository
      model/          # Trip, AssistantRequest, AssistantResponse
      config/         # AwsConfig, LlmConfig
  Dockerfile
  docker-compose.yml
  k8s/
    deployment.yaml
    service.yaml
  .github/
    workflows/
      build.yml
  README.md
  STUDY_PLAN.md       # este arquivo
```

---

## Como Retomar em Outra Sessão

Cole este prompt no Claude:
> "Estou seguindo o plano de estudos do repositório travel-ai-api no GitHub (roger-almeida/travel-ai-api). Estou na [SEMANA X, DIA Y]. Me ajude a continuar."

---

## Links Úteis

- Repositório: https://github.com/roger-almeida/travel-ai-api
- Anthropic API: https://console.anthropic.com
- AWS Free Tier: https://aws.amazon.com/free
- Spring Initializr: https://start.spring.io
- Minikube: https://minikube.sigs.k8s.io
