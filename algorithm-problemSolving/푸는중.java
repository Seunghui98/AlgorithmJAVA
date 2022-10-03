import java.util.*;

class Solution {
    public static class Node {
        Point p1;
        Point p2;
        int dir;
        int dis;
        public Node(Point p1, Point p2, int dir, int dis){
            this.p1 = p1;
            this.p2 = p2;
            this.dir = dir;
            this.dis = dis;
        }
    }
    
    public static class Point {
        int x;
        int y;
    }
    // 상-하-좌-우    
    public static int[] dx = {-1, 1, 0, 0};
    public static int[] dy = {0, 0, -1, 1};
    
    // 현재-우-하-우하
    public static int[] c_dx = {0, 0, 1, 1};
    public static int[] c_dy = {0, 1, 0, 1};
    public static int[][] b;
    public static int ans_min, n;
    public int solution(int[][] board) {
        int answer = 0;
        n = board.length;
        ans_min = Integer.MAX_VALUE;
        
        return answer;
    }
    
    public static void bfs(){
        Queue<Node> q = new LinkedList<>();
        int[][] visited = new int[n][n];
        for(int i=0;i<n;i++){
            Arrays.fill(visited[i], Integer.MAX_VALUE);
        }
        q.add(new Node(new Point(0, 0), new Point(0, 1), 0, 1));
        visited[0][0] = 0;
        
        while(!q.isEmpty()){
            Node node = q.poll();
            if(node.x == n && node.y == n){
                ans_min = Math.min(ans_min, node.dis);
                continue;
            }
            //가로
            if(node.dir == 0){
            // 상, 하, 좌, 우 이동
            for(int d=0;d<4;d++){
                int nx = node.p1.x + dx[d];
                int ny = node.p1.y + dy[d];
                
                
            }
            // 회전
            }
            //세로
            else {
                
            }
            
                
            if(node.)
        }
    }
    
    public boolean check(int x, int y){
        return x >= 0 && y >= 0 && x < n && y < n && b[x][y] == 0;
    }
    
    public boolean check2(int x, int y){
        for(int d=0;d<4;d++){
            int nx = x + c_dx[d];
            int ny = y + c_dy[d];
            if(nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
            if(b[nx][ny] == 1) return false;
        }
        return true;
    }
}
