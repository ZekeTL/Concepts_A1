public class Iter {

    private int index = 0;

    public iterate(Tokens tokens){
        if(tokens == null){
            throw new IllegalArgumentException("List cannot be empty!");
        }
        for (token:tokens){
            //TODO: Do something
        }
    }

    public getNext(Tokens tokens){
        if(tokens[index] == null){
            throw new IndexOutOfBoundsException("Index does not exist!");
        }
        return tokens[index];
        index++;
    }
}
