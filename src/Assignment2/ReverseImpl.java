package Assignment2;

// ReversePOA is a Portable Object Adapter (POA) class
// that provides the base implementation for the Reverse interface.
// The POA is designed to construct object implementations
// that are portable between different ORB products.
import ReverseModule.ReversePOA;

class ReverseImpl extends ReversePOA {
    ReverseImpl() {
        super();
        System.out.println("Reverse Object Created");
    }

    public String reverse_string(String name) {
        StringBuffer str = new StringBuffer(name);
        str.reverse();
        return (("Server Send " + str));
    }
}

