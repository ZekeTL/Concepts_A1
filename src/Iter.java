public class Iter {

    private int index;

    public token getCurrent(token tokens[], int index){
        if(tokens[0] == null){
            throw new IllegalArgumentException("List cannot be empty!");
        }
        else return tokens[index];
    }

    public token getLast(token tokens[]){
        if(tokens[0] == null){
            throw new IllegalArgumentException("List cannot be empty!");
        }

        index--;

        if(tokens[index] == null){
            throw new IndexOutOfBoundsException("Index does not exist!");
        }
        else return tokens[index];
    }

    public token getNext(token tokens[]){
        index++;

        if(tokens[index] == null){
            throw new IndexOutOfBoundsException("Index does not exist!");
        }

        return tokens[index];
    }
}
