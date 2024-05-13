Criando um novo recurso na API com AWS CloudFormation e AWS Lambda
Este guia explica os passos necessários para adicionar um novo recurso à sua API usando AWS CloudFormation e AWS Lambda. Vamos criar um novo endpoint /api/novo-recurso com métodos GET e POST.

Passo 1: Editar cloudformation.yml
Abra o arquivo cloudformation.yml e adicione a seguinte definição de recurso dentro da seção resources:

```
resources:
  Resources:
    NovoRecursoApiGatewayMethod:
      Type: AWS::ApiGateway::Method
      Properties:
        HttpMethod: GET
        ResourceId: !Ref NovoRecursoApiGatewayResource
        RestApiId: !Ref ApiGatewayRestApi
        AuthorizationType: NONE
        Integration:
          Type: AWS_PROXY
          IntegrationHttpMethod: POST
          Uri: !Sub arn:aws:apigateway:${AWS::Region}:lambda:path/2015-03-31/functions/${NovoRecursoLambdaFunction.Arn}/invocations
        MethodResponses:
          - StatusCode: 200
            ResponseModels:
              application/json: Empty
    NovoRecursoApiGatewayResource:
      Type: AWS::ApiGateway::Resource
      Properties:
        ParentId: !GetAtt ApiGatewayRestApi.RootResourceId
        PathPart: novo-recurso
        RestApiId: !Ref ApiGatewayRestApi
    NovoRecursoLambdaFunction:
      Type: AWS::Lambda::Function
      Properties:
        Handler: index.handler
        Role: !GetAtt LambdaExecutionRole.Arn
        Runtime: nodejs14.x
        Code:
          S3Bucket: !Ref S3Bucket
          S3Key: lambda-functions/novo-recurso.zip
```

Substitua os valores específicos, como NovoRecurso, pelos nomes e detalhes adequados do seu recurso.

Passo 2: Editar index.ts
Abra o arquivo index.ts e adicione a seguinte função JavaScript que será executada pela função serverless criada no CloudFormation:


import { APIGatewayProxyEvent, APIGatewayProxyResult } from 'aws-lambda';

export async function handler(event: APIGatewayProxyEvent): Promise<APIGatewayProxyResult> {
    return {
        statusCode: 200,
        body: JSON.stringify({
            message: 'Hello from NovoRecurso!',
            input: event,
        }),
    };
}


Passo 3: Implantação
Após fazer as edições nos arquivos cloudformation.yml e index.ts, você pode usar o carlin para realizar a implantação.

pnpm carlin deploy
