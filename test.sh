
# Define the endpoint URL
URL="http://localhost:9090/api/address/getById/1"

# Number of requests to send
REQUESTS=30

# Function to send requests
send_requests() {
  for ((i=1; i<=REQUESTS; i++)); do
    curl -s -o /dev/null -w "%{http_code}\n" $URL &
    sleep 0.05 # Short delay between requests
  done
}

# Run the function to send requests
send_requests

