### Redux For Android

#### Implementing redux for android


### Usage

#### Adding the dependencies in build.gradle

```
    dependencies {
        compile project(':redux')
    }
```

#### Creating a custom Action class

```
    class CustomAction {
        private String type="CUSTOM";
        public CustomAction() {
          super("CUSTOM");
        }
    }
```
##### CustomAction can also hold some data required for the action

#### Creating reducer

```
    Reducer reducer = new Reducer() {
        public void onReduce(State state,Action action) {
            switch(action.type) {

            }
        }
    }
```

#### Creating store

```
    Store store = Store.createStore(store);
```

#### Subscribing for store changes
```
    store.subscribe(new StoreSubscriber() {
        public void onSubscribe() {

        }
    });
```

#### Dispatching an Action to store

```
    store.dispatch(action);
```
