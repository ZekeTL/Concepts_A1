public class Iter {

    private int index = 0;

    public iterate(TokenList tokens){
        if(tokens == null){
            throw new IllegalArgumentException("List cannot be empty!");
        }
        for (token:tokens){
            //Do something
        }
    }

    public getNext(TokenList tokens){
        if(tokens[index] == null){
            throw new IndexOutOfBoundsException("Index does not exist!");
        }
        return tokens[index];
        index++;
    }
}
