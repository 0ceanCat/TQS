# TQS
# 93442
	lab3
		a) 
			1- EmployeeRepositoryTest.class:
					givenSetOfEmployees_whenFindAll_thenReturnAllEmployees():
						        assertThat(allEmployees).hasSize(3).extracting(Employee::getName).containsOnly(alex.getName(), ron.getName(), bob.getName());


			2- EmployeeRestControllerIT.class:
					whenValidInput_thenCreateEmployee():
						assertThat(found).extracting(Employee::getName).containsOnly("bob");


			3- EmployeeRestControllerTemplateIT.class:
					whenValidInput_thenCreateEmployee():
				        assertThat(found).extracting(Employee::getName).containsOnly("bob");

					givenEmployees_whenGetEmployees_thenStatus200():
					  	assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    					assertThat(response.getBody()).extracting(Employee::getName).containsExactly("bob", "alex");


    		4- etc.


    	b)	
    		Na classe EmployeeService_UnitTest temos o seguinte codigo:
	    		@Mock(lenient = true)
	    		private EmployeeRepository employeeRepository;


    	c)	@Mock cria o objeto anotado e simula comportamentos dele.

    		@MockBean para alem de conseguir fazer o que @Mock faz, permite adicionar o objeto criado em Spring application context (se ja existir um bean da mesma classe, ele é substituído). É usada quando o teste envolve o spring container. No nosso caso, o seguindo mock vai ser injetado no EmployeeRestController.class:
    		
	    		@MockBean
	    		private EmployeeService service;


	    d) application-integrationtest.properties é um ficheiro de configuração, este tipo de ficheiro é usado para carregar configurações necessarias do sistema. Permite-nos alterar configurações do sistema sem mexer no codigo. No application-integrationtest.properties temos configurações essenciais para conseguirmos ligar a mysql, por exemplo url da ligação, username e password. No ex1 (Employee-mngr) não usamos o ficheiro, mas no ex3 ele é lido para ligarmos a mysql.