# EKS Kubernetes Project

This project demonstrates the deployment of two applications on Amazon EKS using Kubernetes. It includes Elasticsearch and Kibana clusters managed with the Elastic Cloud on Kubernetes (ECK) operator.

## Elasticsearch Setup

1. Generate Custom Resource Definitions (CRDs):

    ```bash
    kubectl create -f https://download.elastic.co/downloads/eck/2.10.0/crds.yaml
    ```

2. Install the ECK operator with RBAC rules:

    ```bash
    kubectl apply -f https://download.elastic.co/downloads/eck/2.10.0/operator.yaml
    ```

3. Get an overview of the current Elasticsearch clusters:

    ```bash
    kubectl get elasticsearch
    ```

4. Create an Elasticsearch cluster:

    ```bash
    cat <<EOF | kubectl apply -f -
    apiVersion: elasticsearch.k8s.elastic.co/v1
    kind: Elasticsearch
    metadata:
      name: quickstart
    spec:
      version: 8.11.1
      nodeSets:
      - name: default
        count: 1
        config:
          node.store.allow_mmap: false
    EOF
    ```

## Kibana Setup

1. Create a Kibana cluster:

    ```bash
    cat <<EOF | kubectl apply -f -
    apiVersion: kibana.k8s.elastic.co/v1
    kind: Kibana
    metadata:
      name: quickstart
    spec:
      version: 8.11.1
      count: 1
      elasticsearchRef:
        name: quickstart
    EOF
    ```

2. Check the status of the Elasticsearch cluster:

    ```bash
    kubectl get elasticsearch
    ```

3. Access Kibana locally:

    ```bash
    kubectl port-forward deployment/kibana-kibana 5601
    ```

## Running the Applications

### Hello World Rest API

- Main class: `com.in28minutes.rest.webservices.restfulwebservices.RestfulWebServicesApplication`
- This app is configured to run on port 80.

### Building and Running Containers

- Build the Docker image:

    ```bash
    mvn clean package
    docker build -t atefagyla/my-app:latest .
    ```

- Run the Docker container:

    ```bash
    docker run -p 8200:80 atefagyla/my-app
    ```

## Test URLs

- [http://localhost:8200/hello-world](http://localhost:8200/hello-world)

    ```txt
    Hello World
    ```

- [http://localhost:8200/hello-world-bean](http://localhost:8200/hello-world-bean)

    ```json
    {"message":"Hello World - Changed"}
    ```

- [http://localhost:8200/hello-world/path-variable/in28minutes](http://localhost:8200/hello-world/path-variable/in28minutes)

    ```json
    {"message":"Hello World, in28minutes"}
    ```

## Additional Configuration

- Helm chart installation for Elasticsearch:

    ```bash
    helm install elasticsearch elastic/elasticsearch -f ./values.yaml
    ```

- Port-forwarding for Elasticsearch:

    ```bash
    kubectl port-forward svc/elasticsearch-master 9200
    ```

- Helm chart installation for Kibana:

    ```bash
    helm install kibana elastic/kibana
    ```

- Port-forwarding for Kibana:

    ```bash
    kubectl port-forward deployment/kibana-kibana 5601
    ```

- Elastic credentials for Kibana:

    Username: elastic
    Password: $$$$$$$$$

- Patching Kibana service to use LoadBalancer:

    ```bash
    kubectl patch service kibana -n elk-stack -p '{"spec": {"type": "LoadBalancer"}}'
    ```


