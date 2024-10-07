import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long[] price = new long[N];
        long[] distance = new long[N-1];

        String[] distanceStr = br.readLine().split(" ");
        String[] priceStr = br.readLine().split(" ");

        for(int i=0;i<distanceStr.length;i++) {
            distance[i] = Integer.parseInt(distanceStr[i]);
        }

        for(int i=0;i<priceStr.length;i++) {
            price[i] = Integer.parseInt(priceStr[i]);
        }

        sol(price, distance);
    }

    static void sol(long[] price, long[] distance) {

        long ans = 0;

        for(int i=0;i<price.length-1;i++) {
            if(price[i]<price[i+1]) {
                price[i+1] = price[i];
            }
        }

        for(int i=0;i<distance.length;i++) {
            ans += distance[i] * price[i];
        }
        
        System.out.println(ans);
    }
}
