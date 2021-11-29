build:
	@docker build --rm -t auth0/api_spring-boot_java_hello-world_rbac_authorization .

run: build
	@docker run --rm -it -p "6060:6060" auth0/api_spring-boot_java_hello-world_rbac_authorization