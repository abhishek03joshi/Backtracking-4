// Time complexity:
// Space complexity:
public class Main {

    public static class BuildingPlacement {
        int minDistance, H, W, n;
        int[][] grid;

        public BuildingPlacement (int H, int W, int n) {
            this.H= H;
            this.W = W;
            this.n = n;
            this.minDistance = Integer.MAX_VALUE;
            grid = new int[H][W];
            for (int i = 0; i < H; i++) {
                for (int j = 0; j < W; j++) {
                    grid[i][j] = -1;
                }
            }
        }

        private int findMinDistance() {
            backtrack(0,0, n);
            return minDistance;
        }

        private void findDistance() {
            boolean[][] visited = new boolean[H][W];
            Queue<int[]> q = new LinkedList<>();
            int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

            for (int i = 0; i < H; i++) {
                for (int j = 0; j < W; j++) {
                    if (grid[i][j] == 0) {
                        visited[i][j] = true;
                        q.add(new int[] {i, j});
                    }
                }
            }

            int distance = 0;
            while (!q.isEmpty()) {
                int size = q.size();
                for (int i=0; i < size; i++) {
                    int[] curr = q.poll();
                    for (int[] dir : dirs) {
                        int row = dir[0] + curr[0];
                        int col = dir[1] + curr[1];
                        if (row >=0 && row < H && col>=0 && col < W && !visited[row][col]) {
                            visited[row][col] = true;
                            q.add(new int[] {row, col});
                        }
                    }
                }
                distance++;
            }

            minDistance = Math.min(minDistance, distance - 1);
        }

        private void backtrack(int r, int c, int n) {
            // base case
            if (n == 0) {
                findDistance();
                return;
            }
            if (c == W) {
                c = 0;
                r++;
            }
            // logic
            for (int i = r; i < H; i++) {
                for (int j = c; j < W; j++) {
                    //action
                    grid[i][j] = 0;
                    // recurse
                    backtrack(r, j+1, n-1);
                    // backtrack
                    grid[i][j] = -1;
                }
            }
        }
    }

    public static void main(String[] args) {
        BuildingPlacement bp = new BuildingPlacement(4,4,3);
        int minDistance = bp.findMinDistance();
        System.out.println(minDistance);
    }
}