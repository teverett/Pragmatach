DAOs
------------------------

Relational persistence in Pragmatach is accomplished via plugin [DAOs](http://en.wikipedia.org/wiki/Data_access_object), which support [JPA](http://en.wikipedia.org/wiki/Java_Persistence_API) 1.0.  There are three persistence plugins currently:

* pragmatach-hibernate
* pragmatach-openjpa
* pragmatach-ebean

The DAOs provided by these three plugins all implement the interface

`com.khubla.pragmatach.framework.dao.DAO<T, I extends Serializable>`

The full interface is:

<pre>
<code>
public interface DAO&lt;T, I extends Serializable&gt; {
   long count() throws PragmatachException;

   void delete(T t) throws PragmatachException;

   void deletebyId(I i) throws PragmatachException;

   T findById(I i) throws PragmatachException;

   List&lt;T&gt; getAll() throws PragmatachException;

   List&lt;T&gt; getAll(int start, int count) throws PragmatachException;

   Class&lt;I&gt; getIdentifierClazz();

   Pager&lt;T&gt; getPager(int batchsize) throws PragmatachException;

   Class&lt;T&gt; getTypeClazz();

   void reloadConfig();

   void save(T t) throws PragmatachException;

   void update(T t) throws PragmatachException;
}
</code>
</pre>

JPA annotations are used to build [ORM](http://en.wikipedia.org/wiki/Object-relational_mapping) mappings.  For example:

<pre>
<code>
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ExamplePOJO {

   @Id
   @GeneratedValue
   private Long id;
   private String name;
   
   public Long getId() {
      return id;
   }

   public String getName() {
      return name;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public void setName(String name) {
      this.name = name;
   }
}
</code>
</pre>

A typical example of persisting a class using a Pragmatach DAO, in this case with Hibernate is:

<pre>
<code>
DAO&lt;ExamplePOJO, Long&gt; dao = new HibernateDAO&lt;ExamplePOJO, Long&gt;(ExamplePOJO.class, Long.class);
final ExamplePOJO examplePOJO1 = new ExamplePOJO();
examplePOJO1.setName("abc123");r(34);
dao.save(examplePOJO1);
</code>    
</pre>

Simple CRUD testing of DAOs is supported by the class AbstractDAOTest.  An example of using this class is given  below:

<pre>
<code>
@Test(enabled = true)
public class TestMyExamplePOJO extends AbstractDAOTest&lt;MyExamplePOJO, Long&gt; {
   @Override
   public DAO<MyExamplePOJO, Long> getDAO() {
      return MyExamplePOJO.dao;
   }
 
   @Override
   public Long getId(MyExamplePOJO myExamplePOJO) {
      return myExamplePOJO.getId();
   }
 
   @Override
   public MyExamplePOJO getInstance() {
      return new MyExamplePOJO();
   }
}
</code>    
</pre>

This code will create an instance, save it, retrieve it and perform a number of other simple DAO operations to verify that simple CRUD operations on the DAO work properly.


