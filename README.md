# Hello World API: Spring Boot + Java Sample

This repository contains a Sprint Boot project that defines an API server using Java and Gradle. You'll secure this API with Auth0 to practice making secure API calls from a client application.

## Quick Auth0 Set Up

### Set up the project

Install the project dependencies:

```bash
./gradlew --refresh-dependencies
```

Open the `application.properties` file in `src/main/resources` and update it like so:

```properties
server.port=6060
auth0.audience=
auth0.domain=
spring.security.oauth2.resourceserver.jwt.issuer-uri=https://${auth0.domain}/
```

### Register a Spring Boot API with Auth0

- Open the [APIs](https://manage.auth0.com/#/apis) section of the Auth0 Dashboard.

- Click on the **Create API** button.

- Provide a **Name** value such as _Hello World API Server_.

- Set its **Identifier** to `https://api.example.com` or any other value of your liking.

- Leave the signing algorithm as `RS256` as it's the best option from a security standpoint.

- Click on the **Create** button.

> View ["Register APIs" document](https://auth0.com/docs/get-started/set-up-apis) for more details.

### Connect Express with Auth0

Head back to your Auth0 API page, and follow these steps to get the Auth0 Audience:

![Get the Auth0 Audience to configure an API](https://images.ctfassets.net/23aumh6u8s0i/1CaZWZK062axeF2cpr884K/cbf29676284e12f8e234545de05dac58/get-the-auth0-audience)

- Click on the "Settings" tab.

- Locate the "Identifier" field and copy its value.

- Paste the "Identifier" value as the value of `auth0.audience` in `application.properties`.

Now, follow these steps to get the Auth0 Domain value:

![Get the Auth0 Domain to configure an API](https://images.ctfassets.net/23aumh6u8s0i/37J4EUXKJWZxHIyxAQ8SYI/d968d967b5e954fc400163638ac2625f/get-the-auth0-domain)

- Click on the "Test" tab.

- Locate the section called "Asking Auth0 for tokens from my application".

- Click on the cURL tab to show a mock `POST` request.

- Copy your Auth0 domain, which is part of the `--url` parameter value: `tenant-name.region.auth0.com`.

- Paste the Auth0 domain value as the value of `auth0.domain` in `application.properties`.


**Tips to get the Auth0 Domain**

- The Auth0 Domain is the substring between the protocol, `https://` and the path `/oauth/token`.

- The Auth0 Domain follows this pattern: `tenant-name.region.auth0.com`.

- The `region` subdomain (`au`, `us`, or `eu`) is optional. Some Auth0 Domains don't have it.

### Run the project

With the `application.properties` configuration values set, run the API server by issuing the following command:

```bash
./gradlew bootRun
```

## Test the Protected Endpoints

You can get an access token from the Auth0 Dashboard to test making a secure call to your protected API endpoints.

Head back to your Auth0 API page and click on the "Test" tab.

Locate the section called "Sending the token to the API".

Click on the cURL tab of the code box.

Copy the sample cURL command:

```bash
curl --request GET \
  --url http://path_to_your_api/ \
  --header 'authorization: Bearer really-long-string-which-is-test-your-access-token'
```

Replace the value of `http://path_to_your_api/` with your protected API endpoint path (you can find all the available API endpoints in the next section) and execute the command. You should receive back a successful response from the server.

You can try out any of our full stack demos to see the client-server Auth0 workflow in action using your preferred front-end and back-end technologies.

## API Endpoints

### 🔓 Get public message

```bash
GET /api/messages/public
```

#### Response

```bash
Status: 200 OK
```

```json
{
  "message": "The API doesn't require an access token to share this message."
}
```

### 🔓 Get protected message

> You need to protect this endpoint using Auth0.

```bash
GET /api/messages/protected
```

#### Response

```bash
Status: 200 OK
```

```json
{
  "message": "The API successfully validated your access token."
}
```

### 🔓 Get admin message

> You need to protect this endpoint using Auth0 and Role-Based Access Control (RBAC).

```bash
GET /api/messages/admin
```

#### Response

```bash
Status: 200 OK
```

```json
{
  "message": "The API successfully recognized you as an admin."
}
```
