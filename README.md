Marker interfaces:
- serializable
  ```java
  class Customer implements Serializable {
    private static final long serialVersionUid = 6221240388471619700L;
    private String name;
    //
  }
  ```
  - serialization 
    ```java
    private static final String filepath = "C:\\Users\\DELL\\Desktop\\customer.ser";
     public boolean customerSerialization(Customer customer) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(filepath);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(customer);
            return true;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
    ```
  - deserialization 
    ```java
    private void customerDeserialization(String filepath) {
        try (FileInputStream stream = new FileInputStream(filepath);
             ObjectInputStream inputStream = new ObjectInputStream(stream)) {
            customer = (Customer) inputStream.readObject();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    ```
  
- cloneable
  ```java
  MyClass myClass = new MyClass("Akash");
        try {
            MyClass myClass1 = (MyClass) myClass.clone();
        } catch (CloneNotSupportedException e) {

        } 
  class MyClass implements Cloneable {
    private String name;
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    public MyClass(String name) {
        this.name = name;
    }
   }
   ```
SMS Service Providers:
- Textlocal
- Exotel

Maven Scope values
- compile
- test
- provided
- runtime
- system

maven dependencies:
- from mvn repository
-  from system <scope>system</scope>
  - <systemPath>${project.basedir}/lib/googleauth-1.6.0-SNAPSHOT.jar</systemPath>

load entire Spring application context 
- @SpringBootTest

Bean Scope 
- Singelton
- Prototype
- Request
- session
- Global session
- WebSocket

Design patterns
- Singleton
 - Bean of singelton scope
- prototype
  - Bean of prototype scope
- Factory
  - Bean of factory class with prototype scope

EyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c
- header
- payload
 - claims hashmap
 - organistion
 - issued date
 - expiry date  
- signature



Spring annotations
- @Configuration: soure of bean defination
- @Component: spring Manage component
- Service: specialization of component for readability and understanding of class purpose
- RestController: return data in serialized format (json , xml)
- Controller: return a view

RabbitMqExchangeTypes
- Direct Exchange
- Fanout Exchange 
- Topic Exchange 
- Header Exchange
- Default Exchange
  
Artifacts:
- spring-boot-starter-web:org.springframework.boot
- mysql-connector-java:mysql:org.springframework.boot
- spring-boot-starter-data-jpa
- spring-boot-starter-test:org.springframework.boot

new:
- memory alloation : dynamically allocate memory on heap
- object init with default values: null for refrence type, zero or false for primitive
- constructor invocation: invoke construtor for additional init steps
- return: finally return refrence to the newely created object


Hibernate:
- ddl-auto(data defination language) genration behaviour
 - create : drop exsisting and recreate
 - create-drop: create on startup drop on shutdown
 - update: create if not exsist update(add new column doesn't drop) if changes
 -  validate
 -  none


Memory:
- Stack:
  - Method calls
  - Local variables primitive and refrance variable
  - Method arguments
- Heap:
  - Data
  - Strings in string pool



relationships betwwen table:
- one to one
- many to one
```java
@Entity
  class Customer {
  private int id;
}
@Entity
class Order {
    @Id
    private Long id;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

}
```
- many to many
  ```java
  public class Student {
    //other feilds
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "STUDENT_COURSE_TABLE",
            joinColumns = {
                    @JoinColumn(name = "student_id", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "course_id", referencedColumnName = "id")
            }
    )
    @JsonManagedReference
    private Set<Course> courses;

    }
    public class Course {
    // other feilds
    @ManyToMany(mappedBy = "courses",fetch = FetchType.LAZY)
    @JsonBackReference
    private Set<Student> students;
    }
    ```

jpa: reduce need to write queries explicitly:
- naming conveection
  ```java
  Customer findById(int id);
  Customer findByName(String name);```
- keywords
    ```java
    List<Customer> findByIdIn(List<Integer> id);
    List<Customer> findByAgeLessThanEqual(int age);
    List<Customer> findByAgeGreaterThanEqual(int age);
    List<Customer> findByAgeIsNotNull();
    List<Customer> findByFirstnameContaining(String s);
    List<Customer> findByFirstnameEndingWith(String s);
    List<Customer> findByActiveFalse();
    ```
- custom queries by @Query
 ```java
  @Query("SELECT c FROM Customer c WHERE c.id = :id")
   Customer findById(int id);
  @Query("SELECT c FROM Customer c WHERE c.name = :name")
    Customer findByName(@Param("name") String name);
  @Query("SELECT c FROM Customer c WHERE c.id IN :ids")
    List<Customer> findByIdIn(@Param("ids") List<Integer> id);
  @Query("SELECT c FROM Customer c WHERE c.age <= :age")
    List<Customer> findByAgeLessThanEqual(@Param("age") int age);
  @Query("SELECT c FROM Customer c WHERE c.age >= :age")
    List<Customer> findByAgeGreaterThanEqual(@Param("age") int age);
  @Query("SELECT c FROM Customer c WHERE c.age IS NOT NULL")
    List<Customer> findByAgeIsNotNull();
  @Query("SELECT c FROM Customer c WHERE c.firstname LIKE %:s%")
    List<Customer> findByFirstnameContaining(@Param("s") String s);
  @Query("SELECT c FROM Customer c WHERE c.firstname LIKE %:s")
    List<Customer> findByFirstnameEndingWith(@Param("s") String s);
  @Query("SELECT c FROM Customer c WHERE c.active = false")
    List<Customer> findByActiveFalse();  
  ```



SMTP Configuration:
```yaml
spring:
  mail:
    host: smtp.gmail.com
    port: 587
    username: your_username@gmail.com
    password: your_password
    protocol: smtp
    properties:
      mail:
        smtp:
          auth: true
          starttls:
            enable: true
            required: true
  datasource:
   url: jdbc:mysql://<RDS_ENDPOINT>:<RDS_PORT>/<DATABASE_NAME>
    username: root
    password: <PASSWORD>
  jpa:
   hibernate:
      ddl-auto: update
  data:
    mongodb:
      uri: mongodb+srv://<username>:<password>@<cluster-url>/<database>?retryWrites=true&w=majority
server:
  port: 8082 
```
