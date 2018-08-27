# istio-prototype-example
[Istio service mesh](https://istio.io) in practice

## Running Forecast sample example
- Download Istio (0.8 or higher) 
  and follow the official installation guide.
  Install the CRDs by running the command fro `istio` dir:
  ```bash
  kubectl apply -f install/kubernetes/helm/istio/templates/crds.yaml
  ```
  Run the command following on K8S cluster from the `istio` dir:
  ```bash
  kubectl apply -f install/kubernetes/istio-demo.yaml
  ```
- Deploy the services with injected envoy proxies by running: 
  ```bash
  kubectl apply -f <(istioctl kube-inject -f kubernetes/forecast.yaml)
  ```
- Deploy the Istio Gateway rule:
  ```bash
  kubectl apply -f kubernetes/forecast-ingress.yaml
  ```
- Apply the `DestinationRule` for the deployed services:
  ```bash
  istioctl create -f istio-rules/destination-rules.yaml
  ```

## Applying Istio rules
The following section describes the rules in `/istio-rules` folder.
You can apply and delete each rules by running 
`istionctl create -f /istio-rules/<filename>` and 
`istionctl delete -f /istio-rules/<filename>` respectively.

- `fault-injection-delay-icons.yaml` - inject the 7s delay to the `icon-server` for 100% of the requests
- `fault-injection-error-weather.yaml` - inject HTTP error 500 to the `weather-server` for 100% of the requests
- `request-routing-all-v1.yaml` - route all the requests for `weather-service` and `lunar-phase-server` to version "1.0"
- `request-routing-all-v2.yaml` - route all the requests for `weather-service` and `lunar-phase-server` to version "2.0"
- `request-routing-v2.yaml` - route the requests for `weather-service` and `lunar-phase-server` made for browser Google Chrome to version "2.0" all other requests are routed to version "1.0"
- `request-timeout.yaml` - route all the request for `weather-server` to "1.0" and all the request for `lunar-phase-server` to "2.0". 
Sets the 5s timeout to `lunar-phase-server` for requests made from iPad Android iPhone. (Use with `fault-injection-delay-icons.yaml` to notice the effect).
- `retries-rules-weather.yaml` - route all the request for `weather-server` to "2.0", inject HTTP error 500 for 50% of the requests, set the retries count to 3 with 5s timeout per retry

## Accessing Forecast Service
Istio Gateway URL:
```bash
export INGRESS_PORT=$(kubectl -n istio-system get service istio-ingressgateway -o jsonpath='{.spec.ports[?(@.name=="http2")].nodePort}')
export SECURE_INGRESS_PORT=$(kubectl -n istio-system get service istio-ingressgateway -o jsonpath='{.spec.ports[?(@.name=="https")].nodePort}')
export INGRESS_HOST=$(minikube ip)
export GATEWAY_URL=$INGRESS_HOST:$INGRESS_PORT
curl http://${GATEWAY_URL}/forecast/Lviv
```
