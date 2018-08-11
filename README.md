# Jersey Factory Test
I had a few doubts about how Jersey factories work. I wasn't sure what marking a factory as Singleton but the object it supplies as RequestScoped actually did.
So I designed an experiment to test this. 

I created 10 different objects (found in the [`com.arjvik.arjmart.service`](https://github.com/arjvik/JerseyFactoryTest/tree/master/src/main/java/com/arjvik/arjmart/service) package), 4 service objects instantiated by factories, each of their factories, 
2 service objects instantiated without factories. Each of them logged a message when they were instantiated. This log was stored in a `StringBuilder` and sent to
the browser upon each request, along with the instance number of each service injected by the resource class.

## Classes created:

 - `Service_DefaultFactory_DefaultObject` instantiated by `Factory_DefaultFactory_DefaultObject`
    - [ ] Service object marked as Singleton
    - [ ] Factory object marked as Singleton
 - `Service_DefaultFactory_SingletonObject` instantiated by `Factory_DefaultFactory_SingletonObject`
    - [x] Service object marked as Singleton
    - [ ] Factory object marked as Singleton
 - `Service_SingletonFactory_DefaultObject`	instantiated by `Factory_SingletonFactory_DefaultObject`
    - [ ] Service object marked as Singleton
    - [x] Factory object marked as Singleton
 - `Service_SingletonFactory_SingletonObject` instantiated by `Factory_SingletonFactory_SingletonObject`
    - [x] Service object marked as Singleton
    - [x] Factory object marked as Singleton
 - `Service_DefaultObject` instantiated without a factory
    - [ ] Service object marked as Singleton
 - `Service_SingletonObject` instantiated without a factory
    - [x] Service object marked as Singleton

## What I Discovered

If the service class is marked as Singleton, regardless of whether the factory is Singleton or not, the factory is created only once, and its `get()` method is called exactly once. That resulting service class is cached and given to the resource class with each request. Exactly one instance of the factory and service classes are created.

If the factory class is marked as singleton and the service class is not, the factory is created only once. Its `get()` method is called with each request, and the resulting service class is given to the resource class. Multiple instances of the service classes but only a factory class is created.

If neither the factory or service class is marked as singleton, the factory is created with each request. Each factory instance's `get()` method is called only once. The resulting service class is given to the resource class. One instance of the service class and the factory class is created for every request.

## Output

### First request

#### Classes instantiated:

```
com.arjvik.arjmart.service.Factory_DefaultFactory_DefaultObject - 1
com.arjvik.arjmart.service.Service_DefaultFactory_DefaultObject - 1
com.arjvik.arjmart.service.Factory_DefaultFactory_SingletonObject - 1
com.arjvik.arjmart.service.Service_DefaultFactory_SingletonObject - 1
com.arjvik.arjmart.service.Factory_SingletonFactory_DefaultObject - 1
com.arjvik.arjmart.service.Service_SingletonFactory_DefaultObject - 1
com.arjvik.arjmart.service.Factory_SingletonFactory_SingletonObject - 1
com.arjvik.arjmart.service.Service_SingletonFactory_SingletonObject - 1
com.arjvik.arjmart.service.Service_DefaultObject - 1
com.arjvik.arjmart.service.Service_SingletonObject - 1
com.arjvik.arjmart.MyResource
```

#### Classes injected:

``` 
com.arjvik.arjmart.service.Service_DefaultFactory_DefaultObject - 1
com.arjvik.arjmart.service.Service_DefaultFactory_SingletonObject - 1
com.arjvik.arjmart.service.Service_SingletonFactory_DefaultObject - 1
com.arjvik.arjmart.service.Service_SingletonFactory_SingletonObject - 1
com.arjvik.arjmart.service.Service_DefaultObject - 1
com.arjvik.arjmart.service.Service_SingletonObject - 1
```

### Second request

#### Classes instantiated:

```
com.arjvik.arjmart.service.Factory_DefaultFactory_DefaultObject - 2
com.arjvik.arjmart.service.Service_DefaultFactory_DefaultObject - 2
com.arjvik.arjmart.service.Service_SingletonFactory_DefaultObject - 2
com.arjvik.arjmart.service.Service_DefaultObject - 2
com.arjvik.arjmart.MyResource
```

#### Classes injected:

```
com.arjvik.arjmart.service.Service_DefaultFactory_DefaultObject - 2
com.arjvik.arjmart.service.Service_DefaultFactory_SingletonObject - 1
com.arjvik.arjmart.service.Service_SingletonFactory_DefaultObject - 2
com.arjvik.arjmart.service.Service_SingletonFactory_SingletonObject - 1
com.arjvik.arjmart.service.Service_DefaultObject - 2
com.arjvik.arjmart.service.Service_SingletonObject - 1 
```

### Third request

#### Classes instantiated:

```
com.arjvik.arjmart.service.Factory_DefaultFactory_DefaultObject - 3
com.arjvik.arjmart.service.Service_DefaultFactory_DefaultObject - 3
com.arjvik.arjmart.service.Service_SingletonFactory_DefaultObject - 3
com.arjvik.arjmart.service.Service_DefaultObject - 3
com.arjvik.arjmart.MyResource
```

#### Classes injected:

```
com.arjvik.arjmart.service.Service_DefaultFactory_DefaultObject - 3
com.arjvik.arjmart.service.Service_DefaultFactory_SingletonObject - 1
com.arjvik.arjmart.service.Service_SingletonFactory_DefaultObject - 3
com.arjvik.arjmart.service.Service_SingletonFactory_SingletonObject - 1
com.arjvik.arjmart.service.Service_DefaultObject - 3
com.arjvik.arjmart.service.Service_SingletonObject - 1
```
