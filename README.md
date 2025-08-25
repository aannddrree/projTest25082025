```markdown
# 🚚 Projeto de Cálculo de Frete

Este projeto em **Java** implementa um serviço simples de cálculo de frete com **regras de negócio reais** e inclui **testes unitários com JUnit 5**.  
O objetivo é ensinar conceitos de **Testes Unitários** de forma prática.

---

## 📜 Regras de Negócio

1. **Base por distância:**  
   - R$ **1,20** por **km** percorrido.  

2. **Excesso de peso:**  
   - Até **5 kg**: não há cobrança extra.  
   - Acima de **5 kg**: + R$ **2,00** por kg excedente.  

3. **Item frágil:**  
   - Taxa fixa de **R$ 15,00**.  

4. **Modo expresso:**  
   - Multiplica o valor total do frete por **1,5**.  

5. **Frete grátis:**  
   - Se `valorItens ≥ R$ 200` **e** `distância ≤ 20 km` **e** não for expresso.  

6. **Piso e teto:**  
   - Frete **mínimo**: R$ 10,00.  
   - Frete **máximo**: R$ 300,00.  

7. **Validações:**  
   - Distância e peso não podem ser negativos.  

---

## 📂 Estrutura do Projeto

```

/src
/main/java/br/uniara/frete
Pedido.java
FreteService.java
/test/java/br/uniara/frete
FreteServiceTest.java
pom.xml
README.md

````

---

## ▶️ Executando a Aplicação

O método `main` da classe `FreteService` roda alguns exemplos:

```bash
mvn clean compile exec:java -Dexec.mainClass="br.uniara.frete.FreteService"
````

Saída esperada (exemplo):

```
Frete padrão: R$ 36.0
Frete frágil: R$ 29.0
Frete expresso: R$ 10.0
Frete grátis: R$ 0.0
```

---

## 🧪 Executando os Testes Unitários

Rodar todos os testes:

```bash
mvn test
```

Exemplos de cenários testados:

* Base por distância sem extras.
* Excesso de peso.
* Item frágil.
* Expresso aplicando multiplicador.
* Frete grátis.
* Piso (mínimo R\$ 10,00) e teto (máximo R\$ 300,00).
* Casos inválidos (peso/distância negativos).

---

## 🎯 Objetivo Didático

1. Mostrar como regras de negócio simples podem ser transformadas em código.
2. Ensinar como escrever **testes unitários** para cada regra.
3. Demonstrar o ciclo: **código → teste verde → bug proposital → teste vermelho → correção → verde novamente**.

---

✍️ Autor: Prof. André Silva
Disciplina: **Testes de Software / Engenharia de Software** – UNIARA

```

---

Quer que eu adicione também um **quadro de exemplos prontos** (tabela com pedido → valor esperado) para os alunos compararem manualmente com o resultado dos testes?
```
