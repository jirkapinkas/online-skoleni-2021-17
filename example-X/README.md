# Na serveru se nainstaluje k3s:

    curl -sfL https://get.k3s.io | sh -

# Toto se zavola jednorazove v Kubernetes:

    kubectl apply -f crm.yml -f mailing.yml -f products.yml
    kubectl get pods
    kubectl get deployments
    kubectl describe pod POD_NAME
    kubectl logs POD_NAME

# V adresari example-X zavolat:

    https://gitlab.com/jirkapinkas/micro-products.git
    https://gitlab.com/jirkapinkas/micro-crm.git
    https://gitlab.com/jirkapinkas/micro-mailing.git

# Aplikace jsou zde:

- https://gitlab.com/jirkapinkas/micro-products
- https://gitlab.com/jirkapinkas/micro-crm
- https://gitlab.com/jirkapinkas/micro-mailing

# Build Docker image + push do registry:

    cd micro-XXX
    mvn clean compile com.google.cloud.tools:jib-maven-plugin:2.2.0:build -B -Djib.to.auth.username=TODO_USER -Djib.to.auth.password=TODO_PASS
    NEBO:
    mvn -DregistryUsername=TODO_USER -DregistryPassword=TODO_PASS clean spring-boot:build-image

# Pro nasazeni nove verze aplikace se zavola (na serveru):

    kubectl rollout restart deployment/products-deployment
