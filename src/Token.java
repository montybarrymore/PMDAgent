/**
 * @version 0.0.0
 *          Created by daniel on 18.4.17.
 */
public class Token {
    public enum Type {
        NUMBER, STRING
    }

    private Type type_;
    private Object value_;

    public Token(Type type, Object value) {
        type_ = type;
        value_ = value;
    }

    public boolean isNumber() {
        if(type_ == Type.NUMBER) {
            return true;
        } else {
            return false;
        }
    }

    public String getValue() {
        return value_.toString();
    }
}
