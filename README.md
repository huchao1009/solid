## SOLID设计原则



| **SRP** | The Single Responsibility Principle | 单一责任原则 |
| ------- | ----------------------------------- | ------------ |
| **OCP** | The Open Closed Principle           | 开放封闭原则 |
| **LSP** | The Liskov Substitution Principle   | 里氏替换原则 |
| **DIP** | The Dependency Inversion Principle  | 依赖倒置原则 |
| **ISP** | The Interface Segregation Principle | 接口分离原则 |



### 一、单一职责原则 SRP 

当需要修改某个类的时候原因有且只有一个（THERE SHOULD NEVER BE MORE THAN ONE REASON FOR A CLASS TO CHANGE）。换句话说就是让一个类只做一种类型责任，当这个类需要承当其他类型的责任的时候，就需要分解这个类。



一个类只负责完成一个职责或者功能。也就是说，不要设计大而全的类，要设计粒度小、功能单一的类。换个角度来讲就是，一个类包含了两个或者两个以上业务不相干的功能，那我们就说它职责不够单一，应该将它拆分成多个功能更加单一、粒度更细的类。

如果一个类承担的职责过多，就等于把这些职责[耦合](https://baike.baidu.com/item/耦合/2821124)在一起了。一个职责的变化可能会削弱或者抑制这个类完成其他职责的能力。这种耦合会导致脆弱的设计，当发生变化时，设计会遭受到意想不到的破坏。而如果想要避免这种现象的发生，就要尽可能的遵守单一职责原则。此原则的核心就是[解耦](https://baike.baidu.com/item/解耦)和增强[内聚性](https://baike.baidu.com/item/内聚性/4973441)。

具有可执行性：类中的代码行数、函数或属性过多，会影响代码的可读性和可维护性，我们就需要考虑对类进行拆分；类依赖的其他类过多，或者依赖类的其他类过多，不符合高内聚、低耦合的设计思想，我们就需要考虑对类进行拆分；私有方法过多，我们就要考虑能否将私有方法独立到新的类中，设置为 public 方法，供更多的类使用，从而提高代码的复用性；比较难给类起一个合适名字，很难用一个业务名词概括，或者只能用一些笼统的 Manager、Context 之类的词语来命名，这就说明类的职责定义得可能不够清晰；类中大量的方法都是集中操作类中的某几个属性，比如，在 UserInfo 例子中，如果一半的方法都是在操作 address 信息，那就可以考虑将这几个属性和对应的方法拆分出来。

出现下面这些情况就有可能说明这类的设计不满足单一职责原则：

- 类中的代码行数、函数或者属性过多；
- 类依赖的其他类过多，或者依赖类的其他类过多；
- 私有方法过多；
- 比较难给类起一个合适的名字；
- 类中大量的方法都是集中操作类中的某几个属性。



- Wrong

```java
public class UserInfo {
    private long userId;
    private String username;
    private String email;
    private String telephone;
    private long createTime;
    private long lastLoginTime;
    private String avatarUrl;
    private String provinceOfAddress; // 省
    private String cityOfAddress; // 市
    private String regionOfAddress; // 区
    private String detailedAddress; // 详细地址
    // ...省略其他属性和方法...
}
```

- Right

```java
public class UserInfo {
    private long userId;
    private String username;
    private String email;
    private String telephone;
    private long createTime;
    private long lastLoginTime;
    private String avatarUrl;

    private UserAddress userAddress;
    // ...省略其他属性和方法...
}

class UserAddress{
    private String provinceOfAddress; // 省
    private String cityOfAddress; // 市
    private String regionOfAddress; // 区
    private String detailedAddress; // 详细地址
    // ...省略其他属性和方法...
}
```





### 二、开闭原则 OCP

软件实体应该是可扩展，而不可修改的。也就是说，对扩展是开放的，而对修改是封闭的。



“唯一不变的只有变化本身”。即便我们对业务、对系统有足够的了解，那也不可能识别出所有的扩展点，即便你能识别出所有的扩展点，为这些地方都预留扩展点，这样做的成本也是不可接受的。我们没必要为一些遥远的、不一定发生的需求去提前买单，做过度设计。

最合理的做法是，对于一些比较确定的、短期内可能就会扩展，或者需求改动对代码结构影响比较大的情况，或者实现成本不高的扩展点，在编写代码的时候之后，我们就可以事先做些扩展性设计。但对于一些不确定未来是否要支持的需求，或者实现起来比较复杂的扩展点，我们可以等到有需求驱动的时候，再通过重构代码的方式来支持扩展的需求。



- Wrong

```java
public class RecommendService {
    public static void main(String[] args) {
        new RecommendService().getRecommendList(0);
        new RecommendService().getRecommendList(1);
        new RecommendService().getRecommendList(2);
    }

    public List getRecommendList(int itemType) {
        if (itemType == 0) {
            return queryRestaurantList();
        } else if (itemType == 1) {
            return queryShoppingShopList();
        } else if (itemType == 2) {
            return queryHikingRouteList();
        }
        return null;
    }

    public List queryRestaurantList() {
        System.out.println("查询餐厅列表");
        return null;
    }

    public List queryShoppingShopList() {
        System.out.println("查询购物商店列表");
        return null;
    }

    public List queryHikingRouteList() {
        System.out.println("查询徒步路线列表");
        return null;
    }
}
```

- Right

```java
public class RecommendService {
    public static void main(String[] args) {
        new RecommendService().getRecommendList(0);
        new RecommendService().getRecommendList(1);
        new RecommendService().getRecommendList(2);
    }

    public List getRecommendList(int itemType) {
        RecommendStrategy recommendStrategy = RecommendStrategyFactory.getRecommendStrategy(itemType);
        return recommendStrategy.getRecommendList();
    }
}

interface RecommendStrategy {
    List getRecommendList();
}

class RestaurantRecommendService implements RecommendStrategy {
    @Override
    public List getRecommendList() {
        System.out.println("查询餐厅列表");
        return null;
    }
}

class ShoppingShopRecommendService implements RecommendStrategy {
    @Override
    public List getRecommendList() {
        System.out.println("查询购物商店列表");
        return null;
    }
}

class HikingRouteRecommendService implements RecommendStrategy {
    @Override
    public List getRecommendList() {
        System.out.println("查询徒步路线列表");
        return null;
    }
}

class RecommendStrategyFactory {
    private static final Map<Integer, RecommendStrategy> strategies = new HashMap<>();

    public static RecommendStrategy getRecommendStrategy(Integer entityType) {
        return strategies.get(entityType);
    }

    static {
        strategies.put(0, new RestaurantRecommendService());
        strategies.put(1, new ShoppingShopRecommendService());
        strategies.put(2, new HikingRouteRecommendService());
    }
}
```





### 三、里式替换原则 LSP

里氏替换原则指出，派生的子类应该是可替换基类的，也就是说任何基类可以出现的地方，子类一定可以出现。

子类在设计的时候，要遵守父类的行为约定（或者叫协议）。父类定义了函数的行为约定，那子类可以改变函数的内部实现逻辑，但不能改变函数原有的行为约定。这里的行为约定包括：函数声明要实现的功能；对输入、输出、异常的约定；甚至包括注释中所罗列的任何特殊说明。实际上，定义中父类和子类之间的关系，也可以替换成接口和实现类之间的关系。

- Wrong

```java
public class SquareTest {

    public static void main(String[] args) {
        Rectangle rectangle = new Rectangle();
        rectangle.setLength(4);
        rectangle.setWidth(3);
        System.out.println(rectangle.getArea());

        Rectangle rectangle2 = new Square();
        rectangle2.setLength(4);
        rectangle2.setWidth(3);
        System.out.println(rectangle2.getArea());
    }

}

/**
 * 矩形
 */
class Rectangle {
    int length;
    int width;

    public void setLength(int length) {
        this.length = length;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getArea() {
        return length * width;
    }
}

/**
 * 正方形
 */
class Square extends Rectangle {

    @Override
    public void setLength(int length) {
        this.length = length;
        this.width = length;
    }

    @Override
    public void setWidth(int width) {
        this.width = width;
        this.length = width;
    }
}
```

- Right

```java
public class SquareTest {

    public static void main(String[] args) {
        ClosedFigure square = new Square(3);
        System.out.println(square.getArea());

        ClosedFigure rectangle = new Rectangle(4,3);
        System.out.println(rectangle.getArea());
    }

}

/**
 * 闭合的图形
 * @author double
 * @Date 2021/7/1 11:40 上午
 */
interface ClosedFigure {
    /**
     * 获取图形的面积
     * @return
     */
    int getArea();
}

/**
 * 抽象的矩形
 * @author double
 * @Date 2021/7/1 11:40 上午
 */
abstract class AbstractRectangle implements ClosedFigure {
    /**
     * 获取矩形的长
     * @return
     */
    abstract int getLength();

    /**
     * 获取矩形的宽
     * @return
     */
    abstract int getWidth();
}

/**
 * 长方形
 * @author double
 * @Date 2021/7/1 11:42 上午
 */
class Rectangle extends AbstractRectangle implements ClosedFigure {
    private final int length;
    private final int width;

    Rectangle(int length, int width) {
        this.length = length;
        this.width = width;
    }

    @Override
    int getLength() {
        return length;
    }

    @Override
    int getWidth() {
        return width;
    }

    @Override
    public int getArea() {
        return length * width;
    }
}

/**
 * 正方形
 * @author double
 * @Date 2021/7/1 11:43 上午
 */
class Square extends AbstractRectangle implements ClosedFigure {
    private final int sideLength;

    Square(int sideLength) {
        this.sideLength = sideLength;
    }

    @Override
    int getLength() {
        return sideLength;
    }

    @Override
    int getWidth() {
        return sideLength;
    }

    @Override
    public int getArea() {
        return sideLength * sideLength;
    }

    /**
     * 追加功能，获取边长
     */
    int getSideLength() {
        return sideLength;
    }
}
```





### 四、接口隔离原则 ISP

接口隔离原则（ISP）表明类不应该被迫依赖他们不使用的方法，也就是说一个接口应该拥有尽可能少的行为，它是精简的，也是单一的。

理解接口隔离原则的关键，就是理解其中的“接口”二字。在这条原则中，我们可以把“接口”理解为下面三种东西：

- 一组 API 接口集合

- 单个 API 接口或函数

- OOP 中的接口概念

在设计微服务或者类库接口的时候，如果部分接口只被部分调用者使用，那我们就需要将这部分接口隔离出来，单独给对应的调用者使用，而不是强迫其他调用者也依赖这部分不会被用到的接口。

- Wrong

```java
public interface UserService {
    boolean register(String cellphone, String password);

    boolean login(String cellphone, String password);

    UserInfo getUserInfoById(long id);

    UserInfo getUserInfoByCellphone(String cellphone);
  
    boolean deleteUserByCellphone(String cellphone);
  
    boolean deleteUserById(long id);
}
```

- Right

```java
public interface UserService {
    boolean register(String cellphone, String password);

    boolean login(String cellphone, String password);

    UserInfo getUserInfoById(long id);

    UserInfo getUserInfoByCellphone(String cellphone);
}

public interface RestrictedUserService {
    boolean deleteUserByCellphone(String cellphone);
  
    boolean deleteUserById(long id);
}
```





### 五、依赖倒置原则 DIP

高层模块不要依赖低层模块。高层模块和低层模块应该通过抽象来互相依赖。抽象不应该依赖于细节，细节应该依赖于抽象。

- Wrong

```java
public class LoggerTest{

    public static void main(String[] args) {
        Logger logger = new Logger();
        logger.info("this is info text");
        logger.warn("this is warn text");
        logger.debug("this is debug text");
        logger.error("this is error text");
    }
}
 class Logger {

    public void info(String text) {
        System.out.println(String.format("%s %s %s", LocalDateTime.now(), "info", text));
    }

    public void warn(String text) {
        System.out.println(String.format("%s %s %s", LocalDateTime.now(), "warn", text));
    }

    public void debug(String text) {
        System.out.println(String.format("%s %s %s", LocalDateTime.now(), "debug", text));
    }

    public void error(String text) {
        System.out.println(String.format("%s %s %s", LocalDateTime.now(), "error", text));
    }
}
```

- Right

```java
public class LoggerTest {
    public static void main(String[] args) {
//        ILogger logger = new ConsoleLogger();
//        ILogger logger = new FileLogger();
        ILogger logger = new KafkaLogger();
        logger.info("this is info text");
        logger.warn("this is warn text");
        logger.debug("this is debug text");
        logger.error("this is error text");
    }
}

interface ILogger {
    void info(String text);

    void debug(String text);

    void warn(String text);

    void error(String text);
}

class ConsoleLogger implements ILogger {

    @Override
    public void info(String text) {
        System.out.println(String.format("%s %s %s %s", "[console]", LocalDateTime.now(), "info", text));
    }

    @Override
    public void debug(String text) {
        System.out.println(String.format("%s %s %s %s", "[console]", LocalDateTime.now(), "debug", text));
    }

    @Override
    public void warn(String text) {
        System.out.println(String.format("%s %s %s %s", "[console]", LocalDateTime.now(), "warn", text));
    }

    @Override
    public void error(String text) {
        System.out.println(String.format("%s %s %s %s", "[console]", LocalDateTime.now(), "error", text));
    }
}

class FileLogger implements ILogger {

    @Override
    public void info(String text) {
        System.out.println(String.format("%s %s %s %s", "[file]", LocalDateTime.now(), "info", text));
    }

    @Override
    public void debug(String text) {
        System.out.println(String.format("%s %s %s %s", "[file]", LocalDateTime.now(), "debug", text));
    }

    @Override
    public void warn(String text) {
        System.out.println(String.format("%s %s %s %s", "[file]", LocalDateTime.now(), "warn", text));
    }

    @Override
    public void error(String text) {
        System.out.println(String.format("%s %s %s %s", "[file]", LocalDateTime.now(), "error", text));
    }
}

class KafkaLogger implements ILogger {

    @Override
    public void info(String text) {
        System.out.println(String.format("%s %s %s %s", "[kafka]", LocalDateTime.now(), "info", text));
    }

    @Override
    public void debug(String text) {
        System.out.println(String.format("%s %s %s %s", "[kafka]", LocalDateTime.now(), "debug", text));
    }

    @Override
    public void warn(String text) {
        System.out.println(String.format("%s %s %s %s", "[kafka]", LocalDateTime.now(), "warn", text));
    }

    @Override
    public void error(String text) {
        System.out.println(String.format("%s %s %s %s", "[kafka]", LocalDateTime.now(), "error", text));
    }
}
```



- 正常依赖图

![9571610-1970cb0205d4a656](https://tva1.sinaimg.cn/large/008i3skNgy1gs1ir52zvoj30k40gdn2j.jpg)

- 依赖倒置后的图

![9571610-9917220623b99e19](https://tva1.sinaimg.cn/large/008i3skNgy1gs1iqu699oj30k00gntdy.jpg)