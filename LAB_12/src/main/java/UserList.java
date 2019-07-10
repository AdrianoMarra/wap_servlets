
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class UserList {

    private Map<Integer, User> list = new HashMap<Integer, User>();

    public void addUser(User user){
        this.list.put(user.getKey(), user);
    }

    public Map<Integer, User> getList() {
        return list;
    }

    public boolean validate(String name, String pass){
        Integer key = Objects.hash(name, pass);
        User user;
        if(list.containsKey(key)){
            user = list.get(key);
            if(user.getName().equals(name) && user.getPassword().equals(pass))
                return true;
        }
        return false;
    }

}
