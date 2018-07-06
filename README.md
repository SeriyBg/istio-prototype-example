# istio-prototype-example
[Istio service mesh](https://istio.io) in practice

## Running Forecast sample example
- Download Istio (0.8 or higher) 
  and follow the official installation guide.
  Run the command following on K8S cluster from the `istio` dir:
  ```kubectl apply -f install/kubernetes/istio-demo.yaml```
- Deploy the services with injected envoy proxies by running: 
  ```ubectl apply -f <(istioctl kube-inject -f kubernetes/forecast.yaml)```
- Apply the `DestinationRule` for the deployed services:
  ```istioctl create -f istio-rules/destination-rules.yaml```

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
