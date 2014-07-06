public class Subset {
       public static void main(String[] args) {
         
            if (args.length < 1)
               throw new java.lang.IllegalArgumentException();
          
           int N = Integer.parseInt(args[0]);
           
           if (N < 0)
               throw new java.lang.IllegalArgumentException();

           RandomizedQueue<String> queue = new RandomizedQueue<String>();

           String[] strings = StdIn.readStrings();
           
           for (String str : strings) {
               queue.enqueue(str);
           }
           for (int i = 0; i < N; i++) {
                StdOut.println(queue.dequeue());
           }
       }
}
