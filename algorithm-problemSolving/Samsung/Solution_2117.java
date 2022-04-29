// SWEA - 홈 방범 서비스 (2117번)
// 구현

BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		int TC = Integer.parseInt(br.readLine());
		for(int test_case=1;test_case<=TC;test_case++) {
			st = new StringTokenizer(br.readLine(), " ");
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			map = new int[n][n];
			list = new ArrayList<>();
			for(int i=0;i<n;i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j=0;j<n;j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j] == 1) {
						list.add(new int[] {i, j});
					}
				}
			}
			int ans = 1;
			for(int k=2;k<=n+1;k++) {
				for(int i=0;i<n;i++) {
					for(int j=0;j<n;j++) {
						int cnt = 0;
						for(int[] arr:list) {
							int x = arr[0];
							int y = arr[1];
							if((Math.abs(i-x) + Math.abs(j-y) <= k-1)) {
								cnt++;
							}
						}
						
						if(cnt*m >= k*k + (k-1)*(k-1)) {
							ans = Math.max(ans, cnt);
						}
					}
				}
			}
			
			sb.append("#"+test_case+" ").append(ans+"\n");
		}
		System.out.println(sb.toString());
	}
