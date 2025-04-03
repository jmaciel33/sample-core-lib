# Sample - core-lib

## Stack

- Java
- Quarkus

## Como Utilizar?

Siga os passos abaixo para utilizar a lib.

Adicione o repositorio ao POM.

```
    <distributionManagement>
        <repository>
            <id>github</id>
            <name>GitHub Packages</name>
            <url>https://maven.pkg.github.com/jmaciel33/sample-core-lib</url>
        </repository>
    </distributionManagement>
```

Depois adicione a lib a lista de dependencia do POM:


```
        <dependency>
            <groupId>io.jmaciel</groupId>
            <artifactId>sample-core-lib</artifactId>
            <version>1.0.0</version>
        </dependency>
```

Com o seu nome de usuário do GitHub e o token de acesso pessoal, configure o arquivo settings.xml do Maven como segue:

```
<settings>
    <servers>
        <server>
            <id>github</id>
            <username>seu-username</username>
            <password>seu-token</password>
        </server>
    </servers>
</settings>
```

### Setup Lib no projeto

A Lib possui algumas configurações que devem ser feitas no projeto que a utilizará.

No application.yaml do seu projeto adicione as seguintes configurações:

```
core-lib:
    traceability-headers:
      enabled: true # Habilita ou desabilita a adição de headers de traceabilidade.
    jwt:
      enabled: true # Habilita ou desabilita a validação do token JWT.
      jwks-url: http://localhost:8080/realms/av-pay/protocol/openid-connect/certs # URL do JWKS do Keycloak para validação token.
```


### Como colaborar?

Abra um PR :)