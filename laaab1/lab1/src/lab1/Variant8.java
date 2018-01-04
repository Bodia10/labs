package lab1;

public class Variant8 {
		public static class Date{
			private int day;
			private int month;
	                
	                public Date()
	                {};
			public void setDay(int day) {
				this.day = day;
			}

			public void setMonth(int month) {
				this.month = month;
			}

			public int getDay() {
				return day;
			}

			public int getMonth() {
				return month;
			}
	                public Date(int d, int m)
	                {
	                    day=d;
	                    month=m;
	                }
			@Override
			public boolean equals (Object other){
				Date dj = (Date)other;
				return ((day == dj.day) && (month == dj.month));
			}
		}

		public static class IntNumbers{
			private int a;
			private int b;

			public void setA(int a) {
				this.a = a;
			}

			public void setB(int b) {
				this.b = b;
			}

			public int getA() {
				return a;
			}

			public int getB() {
				return b;
			}

			@Override
			public boolean equals (Object other){
				IntNumbers dp = (IntNumbers)other;
				return ((a == dp.a) && (b == dp.b));
			}
		}


		
		public float beginTask(float a, float b) {
			return (a + b) / 2;
		}
		
		

		public int integerNumbersTask(int n) {
			assert (n > 0) && (n < 99): "Argument n should be more than 0 and less than 99";
			float number = (float) n;
			number /= 10;
			int S = (int) number;
			float f = number - S;
			f *= 100;
			int F = (int) f;
			n = F + S;
			return n;
		}

		
		public boolean booleanTask(int a, int b) {
			return ((a % 2 != 0) && (b % 2 != 0)) ;
			
		}
		
		
		
		public IntNumbers ifTask(int a, int b) {
			IntNumbers n = new IntNumbers();
			n.setA(a);
			n.setB(b);

			if (a > b){
				n.getA();
				n.getB();
			}else{
				n.getB();
				n.getA();
			}
			return n;
		}

	        
		
		public Date switchTask(int d, int m) {
			Date dd = new Date();
			switch(d) {
				case 1:
					switch (m) {
	                case 1:
	                        dd.setDay(31);
							dd.setMonth(12);
							dd.getDay();
							dd.getMonth();
							break;
						case 2:
						case 4:
						case 6:
						case 8:
						case 9:
						case 11:
							dd.setDay(31);
							dd.setMonth(m - 1);
							break;

						case 3:
							dd.setDay(28);
							dd.setMonth(2);
							dd.getDay();
							dd.getMonth();
							break;

						default:
							dd.setDay(30);
							dd.setMonth(m - 1);
							dd.getDay();
							dd.getMonth();
							break;
					}
				break;


				default:
					dd.setDay(d - 1);
					dd.setMonth(m);
					dd.getDay();
					dd.getMonth();
					break;

			}
			return dd;
		}


		
		public double forTask(int a, int b) {
			assert a < b: "Argument b should be more than a";
			int p = 1;
			for (int i = a; i <= b; i++){
				p = p * i;
			}
			return p;
		}

		

		public int whileTask(int n) {
			assert n > 0: "Argument should be more than zero";
			int k = 0;
			while (k*k <= n)
				k++;
			return k-1;
		}

		

		public double arrayTask(double[] array) {
			double q = 0;
			int k = 0;
			q = array[1] / array[0];
			for (int i = 0; i < array.length-1; i++){
				if (array[i+1] / array[i] == q)
					k++;
			}
			if (k == array.length - 1)
				return q;
			return 0;
		}

		public int[][]  twoDimensionArrayTask(int[][] array,  int N) {
			int k = 0;
			int q = 0;
	                int n = array.length;
	                int m=array[0].length;
			for (int i = 0; i < n; i++){
				if (k == m){
					q = i - 1;
					break;
				}
				k = 0;
				for (int j = 0; j < m; j++){
					if(array[j][i] < 0) k++;
				}
			}
			if (k == m) {
				for (int i = 0; i < n; i++) {
					int num = array[i][q];
					array[i][q] = array[i][N - 1];
					array[i][N - 1] = num;
				}
			}

			return array;

		}

		public static void main(String... strings) {
			System.out.println("Done!!!");
		}

	}