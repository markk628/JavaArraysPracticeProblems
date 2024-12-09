package JavaCodingProblems;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class ArrayPracticeProblems {	
	private class NegativeNumberException extends Exception {
	      public NegativeNumberException(String message) {
	    	  super(message);
	      }
	}
	
	private class NotAnOddNumberOrNumberGreaterThanTwoException extends Exception {
	      public NotAnOddNumberOrNumberGreaterThanTwoException(String message) {
	    	  super(message);
	      }
	 }
	
	private class NotAYesOrNoException extends Exception {
	      public NotAYesOrNoException(String message) {
	    	  super(message);
	      }
	 }
	
	private Scanner scanner;
	
	public ArrayPracticeProblems() { 
		this.scanner = new Scanner(System.in);
	}
	
	// 사용자가 정수가 아닌 문자열이나 문자를 입력하면 나타나는 오류를 처리하는 function
	private int customParseInt() {
		try {
			return Integer.parseInt(scanner.nextLine());
		} catch (NumberFormatException e) {
			System.out.print("enter a NUMBER: ");
			return customParseInt();
		}
	}
	
	private int[] bubbleSort(int[] listToSort) {
		int temporaryValue;
		// 0에서 listToSort의 크기 - 1 까지 반복
		for (int i = 0; i < (listToSort.length - 1); i++) {
			// listToSort의 i값이 listToSort의 i + 1값보다 크면 값을 교환한다
			if (listToSort[i] > listToSort[i + 1]) {
				temporaryValue = listToSort[i];
				listToSort[i] = listToSort[i + 1];
				listToSort[i + 1] = temporaryValue;
				// 모든왼쪽값이 바로 오른쪽에 있는 값보다 작을때까지 교환하게 bubbleSort를 다시 호출
				return bubbleSort(listToSort);
			}
		}
		return listToSort;
	}
	
	// 반복문을 사용해서 charToCheck 문자가 stringToCheck문자열에 있는지 여부를 나타내는 부울을 반환하는 function
	private boolean isInString(String stringToCheck, char charToCheck) { 
		for (char letter : stringToCheck.toCharArray()) {
			if (letter == charToCheck) {
				return true;
			}
		}
		return false;
	}
	
	// 문자열 answer의 첫번제 문자가 y나 n이 아닐 경우에 오류를 던지는 function
	private boolean userWouldLikeToContinue(String answer) throws NotAYesOrNoException {
		// 문자열 answer의 첫번제 문자
		char firstLetterOfAnswer = answer.toLowerCase().charAt(0);
		// firstLetterOfAnswer가 y나 n이 아니면 오류를 던진다
		if (firstLetterOfAnswer != 'y' && firstLetterOfAnswer != 'n') {
			throw new NotAYesOrNoException("Answer with \"yes\" or \"no\"");
		}
		// firstLetterOfAnswer가 y이면 true를 출력, 아니면 false
		return firstLetterOfAnswer == 'y'? true : false;
	}
	
	// 사용자가 입력한 답이 "yes", "y", "no" "n" (대문자 소문자 상관없이) 아닐 경우에 나타나는 오류를 처리하는 function
	private boolean handleYesOrNoError() {
		// 사용자의 답을 저장할 문자열 변수
		String userAnswer;
		try {
			System.out.print("Would you like to continue?: ");
			userAnswer = scanner.nextLine();
			return userWouldLikeToContinue(userAnswer);
		} catch (NotAYesOrNoException e) {
			System.out.println(e.getMessage());
			return handleYesOrNoError();
		}
	}
	
	private void startProgram() {
		System.out.print("Please enter the question number you'd like to test. If you'd like to test all questions, type \"all\": ");
		String testingPreference = scanner.nextLine();
		if (testingPreference.toLowerCase().equals("all")) {
			printAllAnswers();
		} else {
			try {
				int number = Integer.parseInt(testingPreference);
				printAnswer(number);
			} catch (NumberFormatException e) {
				System.out.println(e.getMessage());
				startProgram();
			}
		}
		scanner.close();
	}
 	
	private void printAnswer(int problemNumber) {
		switch (problemNumber) {
		case 1:
			System.out.println("1. " + Arrays.toString(problem1()));
			break;
		case 2:
			System.out.println("2. " + Arrays.toString(problem2()));
			break;
		case 3:
			try {
				System.out.print("Please enter your desired array length for problem 3: ");
				int arrayLength = customParseInt();
				System.out.println("3. " + Arrays.toString(problem3(arrayLength)));
			} catch (NegativeNumberException e) {
				System.out.println(e.getMessage());
				printAnswer(3);
			}
			break;
		case 4:
			System.out.println("4. " + problem4());
			break;
		case 5:
			System.out.print("Please enter your desired word for problem 5: ");
			String word =  scanner.nextLine().toLowerCase();
			System.out.print("Please enter your desired letter for problem 5: ");
			char letter =  scanner.nextLine().toLowerCase().charAt(0);
			int[] problem5Answer = problem5v2(word, letter);
			System.out.println("5. " + "letter count: " + problem5Answer.length + ", at indices: " + Arrays.toString(problem5Answer));
			break;
		case 6:
			System.out.print("Please enter a number between 0-6 for problem 6: ");
			int index = customParseInt();
			System.out.println("6. " + problem6(index));
			break;
		case 7:
			System.out.print("Please enter the amount of numbers you want to enter for problem 7: ");
			int numberOfNumbers = customParseInt();
			System.out.println("7. " + problem7(scanner, numberOfNumbers));
			break;
		case 8:
			try {
				System.out.print("Please enter an odd number greater than 2 for problem 8: ");
				int oddNumber = customParseInt();
//				System.out.println("8. " + problem8(oddNumber));
				System.out.println("8. " + Arrays.toString(problem8v2(oddNumber)));
			} catch (NotAnOddNumberOrNumberGreaterThanTwoException e) {
				System.out.println(e.getMessage());
				printAnswer(8);
			}
			break;
		case 9:
			String menu = "Metal Chicken, Rubber Chicken, Fake Chicken, Glass Chicken, Trash Chicken, Recycled Chicken";
			System.out.print("Welcome to Chillis. we serve " + menu + ". What do you want: ");
			String menuItem = scanner.nextLine();
			System.out.println("9. " + problem9(menu, menuItem));
			break;
		case 10:
			System.out.println("10. " + problem10());
			break;
		case 11:
			System.out.println("11. " + Arrays.toString(problem11()));
			break;
		case 12:
			System.out.println("12. " + problem12());
			break;
		case 13:
			System.out.println("13. " + Arrays.toString(problem13(10, 1, 11)));
			break;
		case 14:
			System.out.println("14. " + Arrays.toString(problem14()));
			break;
		case 15:
			System.out.print("Please enter a string for problem 15: ");
			String stringToEnter = scanner.nextLine();
			System.out.println("15. " + problem15(stringToEnter));
			break;
		case 16:
			System.out.println("16. " + Arrays.toString(problem16v2()));
			break;
		default:
			System.out.println("there are only 16 problems");
		}
	}
	
	private void printAllAnswers() {
		for (int i = 1; i < 17; i++) {
			printAnswer(i);
		}
	}
	
//	1. 길이가 10인 배열을 선언하고 1부터 10까지의 값을 반복문을 이용하여 순서대로 배열 인덱스에 넣은 후 그 값을 출력하세요.
	int[] problem1() {
		// 값을 저장할 길이가 10인 배열
		int[] answer = new int[10];
		// 반복문으로 0에서 10까지 반복
		for (int i = 0; i < 10; i++) {
			// 반복할때마다 answer배열의 index자리에 index + 1 저장
			answer[i] = i + 1;
		}
		return answer;
	}
	
//	2. 길이가 10인 배열을 선언하고 1부터 10까지의 값을 반복문을 이용하여 역순으로 배열 인덱스에 넣은 후 그 값을 출력하세요.
	int[] problem2() {
		// 값을 저장할 길이가 10인 배열
		int[] answer = new int[10];
		// 반복문으로 10에서 0까지 반복
		for (int i = 10; i > 0; i--) {
			// 반복할때마다 answer배열의 10 - index자리에 index 저장
			answer[10 - i] = i;
		}
		return answer;
	}
	
	int[] problem3(int arrayLength) throws NegativeNumberException {
		// arrayLength가 음수면 오류
		if (arrayLength < 0) {
			throw new NegativeNumberException("Enter a positive number");
		}
		// 값을 저장할 길이가 arrayLength인 배열
		int[] answer = new int[arrayLength];
		// 반복문으로 0에서 arrayLength까지 반복
		for (int i = 0; i < arrayLength; i++) {
			// 반복할때마다 answer배열의 index자리에 index + 1 저장
			answer[i] = i + 1;
		}
		return answer;
	}
	
//	4. 길이가 5인 String배열을 선언하고 "사과”, "귤", "포도", "복숭아”, "참외"로 초기화 한 후 배열 인덱스를 활용해서 귤을 출력하세요.
	String problem4() {
		// 과일 string배열 선언
		String[] listOfFruits = {"사과", "귤", "포도", "복숭아", "참외"};
		// listOfFruits의 index 1에 있는 값 출 
		return listOfFruits[1];
	}
	String problem4v2() {
		String[] fruitsToEnter = {"사과", "귤", "포도", "복숭아", "참외"};
		String[] listOfFruits = new String[fruitsToEnter.length];
		Optional<Integer> indexForTangerine = Optional.empty();
		for (int i = 0; i < fruitsToEnter.length; i++) {
			if (fruitsToEnter[i] == "귤") {
				indexForTangerine = Optional.of(i);
			}
			listOfFruits[i] = fruitsToEnter[i];
		}
		if (indexForTangerine.isEmpty()) {
			return "not in list";
		}
		return listOfFruits[indexForTangerine.get()];
	}
	
//	5. 문자열을 입력 받아 문자 하나하나를 배열에 넣고 검색할 문자가 문자열에 몇 개 들어가 있는지 개수와 몇 번째 인덱스에 위치하는지 인덱스를 출력하세요.
	int[] problem5(String word, char letter) {
		// word에 letter가 나타나는 횟수를 저장할 변수
		int countForLetterInWord = 0;
		// 컴퓨터가 word의 전체 길이를 반복할 필요 없이 word에 letter가 나타나는 횟수만큼만 반복할 수 있도록 반복 횟수를 저장하는 변수
		int count = 0;
		// 답을 저장하고 출력할 변수
		int[] answer;
		//	입력 받은 문자열의 문자 하나하나를 저장할 배열
		char[] wordCharsList = new char[word.length()];
		
		// 입력된 단어의 문자들을 wordCharsList배열에 넣고 검색할 문자가 몇 개 들어있는지 countForLetterInWord에 저장
		for (int i = 0; i < word.length(); i++) {
			wordCharsList[i] = word.charAt(i);
			if (wordCharsList[i] == letter) {
				countForLetterInWord += 1;
			}
		}
		
		// 검색할 문자 개수로 생성한 정수배열을 answer에 대입
		answer = new int[countForLetterInWord];
		
		// 입력된 단어의 문자들이 검색할 문자와 같으면 answer의 index자리에 대입하고 반복 횟수에 1을 더한다
		for (int i = 0; i < word.length(); i++) {
			if (wordCharsList[i] == letter) {
				answer[count] = i;
				count += 1;
				// 첫번쩨 반복문에 연산한 letter횟수가 두번쩨 반복문에 연산한 반복횟수와 같으면 반복문 중지
				if (count == countForLetterInWord) {
					break;
				}
			}
		}
		return answer;
	}
	int[] problem5v2(String word, char letter) {
		String indices = "";
		List<String> listOfChars;
		IntStream streamOfInts;
		char[] wordCharsList = new char[word.length()];
		
		// 입력된 단어의 문자들을 wordCharsList배열에 넣코 검색할 문자에 도달하면 index를 indices 문자열에 더한다
		for (int i = 0; i < word.length(); i++) {
			wordCharsList[i] = word.charAt(i);
			if (wordCharsList[i] == letter) {
				// indices 문자열이 비여있으면 i만 더하고 아니면 ","와 i를 같이 더한다
				indices += indices.isEmpty() ? i : ("," + i);
			}
		}
		// indices문자열을 문자배열로 변경하고 List wrapper넣는다 (stream, map, 그리고 mapToInt 쓰기위해)
		listOfChars = List.of(indices.split(","));
		// map로 문자들을 정수object (Integer)로 변환, mapToInt로 Integer을 int로 변환 (궁극적으로 streamOfInts의 타입은 IntStream)
		streamOfInts = listOfChars.stream()
										.map(n -> Integer.parseInt(n))
										.mapToInt(Integer::intValue);
		return streamOfInts.toArray();
	}
	
//	6. “월“ ~ “일”까지 초기화된 문자열 배열을 만들고 0부터 6까지 숫자를 입력 받아 입력한 숫자와 같은 인덱스에 있는 요일을 출력하고 범위에 없는 숫자를 입력 시 “잘못 입력하셨습니다“를 출력하세요.
	String problem6(int day) {
		// 요일 이름 문자열변수
		String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
		// 입력한 정수가 days배열의 기리보다 적고, 정수가 음수가 아니면 days배열의 입력한 정수index 자리에 있는 문자열 출력
		if (day < days.length && day > -1) {
			return days[day];
		}
		// 잘못 입력했으면 새로운 정수로 problem6다시 호출
		System.out.println("잘못 입력하셨습니다");
		System.out.print("Please enter a number between 0-6 for problem 6: ");
		int index = customParseInt();
		return problem6(index);
	}
	
	
//	7. 사용자가 배열의 길이를 직접 입력하여 그 값만큼 정수형 배열을 선언 및 할당하고 배열의 크기만큼 사용자가 직접 값을 입력하여 각각의 인덱스에 값을 초기화 하세요. 그리고 배열 전체 값을 나열하고 각 인덱스에 저장된 값들의 합을 출력하세요
	int problem7(Scanner scanner, int arrayLength) {
		// 입력한 배열크기로 합할 정수들을 저장하는 배열 생성
		int[] numsToSum = new int[arrayLength];
		// 합을 저장할 정수변수
		int sum = 0;
		// 합할 정수를 저장할 정수변수
		int numToAdd;
		// 0에서 입력한 배열크기까지 반복
		for (int i = 0; i < arrayLength; i++) {
			// 새 정수를 입력받아 numsToSum에 저장하고 입력받은 정수를 sum에 더한다 
			System.out.print("Please enter a number for index " + i + ": ");
			numToAdd = customParseInt();
			numsToSum[i] = numToAdd;
			sum += numToAdd;
		}
		return sum;
	}
	
//	8. 3이상인 홀수 자연수를 입력 받아 배열의 중간까지는 1부터 1씩 증가하여 오름차순으로 값을 넣고, 중간 이후부터 끝까지는 1씩 감소하여 내림차순으로 값을 넣어 출력하세요. 단, 입력한 정수가 홀수가 아니거나 3 미만일 경우 “다시 입력하세요”를 출력하고 다시 정수를 받도록 하세요.
	String problem8(int number) throws NotAnOddNumberOrNumberGreaterThanTwoException {
		// number가 짝수거나 3보다 작으면 오류
		if ((number % 2 == 0) || (number < 3)) {
			throw new NotAnOddNumberOrNumberGreaterThanTwoException("Not an odd number or number is not greater than 2");
		}
		// 답을 저장할 문자열변수
		String answer = "";
		// number의 반올림한 가운대
		int middle = (number / 2) + 1;
		// 0에서 middle까지 반복
		for (int i = 1; i < middle; i++) {
			// answer에 i를 더한다
			answer += i;
		}
		// middle에서 0까지 반복
		for (int i = middle; i > 0; i--) {
			// answer에 i를 더한다
			answer += i;
		}
		return answer;
	}
	
	int[] problem8v2(int number) throws NotAnOddNumberOrNumberGreaterThanTwoException {
		// number가 짝수거나 3보다 작으면 오류
		if ((number % 2 == 0) || (number < 3)) {
			throw new NotAnOddNumberOrNumberGreaterThanTwoException("Not an odd number or number is not greater than 2");
		}
		// number의 반올림한 가운대
		int middle = (number / 2) + 1;
		// 2차원 배열 생성 (twoDimentionList의 두 배열을 합할꺼다)
		int[][] twoDimentionList = new int[2][middle - 1];
		// 출력할 정수배열
		int[] answer;
		// 0에서 2까지 반복
		for (int i = 0; i < 2; i++) {
			// i가 0이면 (2차원 배열의 첫번째 배열이면)
			if (i == 0) {
				// 0에서 2차원 배열의 첫번째 배열의 크기까지 반복
				for (int j = 0; j < (twoDimentionList[i].length); j++) {
					// 2차원 배열의 첫번째 배열의 j자리에 j + 1 대입
					twoDimentionList[i][j] = j + 1;
				}
				// i가 1이면 (2차원 배열의 두번째 배열이면)
			} else {
				// 0에서 2차원 배열의 두번째 배열의 크기까지 반복
				for (int j = 0; j < (twoDimentionList[i].length); j++) {
					// 2차원 배열의 두번째 배열의 j자리에 middle - j - 1 대입
					twoDimentionList[i][j] = middle - j - 1;
				}
			}
		}
		// 2차원 배열의 배열 크기의 2배 + 1 크기의 정수배열을 생선하고 answer에 대입
		answer = new int[(twoDimentionList[0].length * 2) + 1];
		// 0에서 2까지 반복
		for (int i = 0; i < 2; i++) {
			// i가 0이면 (2차원 배열의 첫번째 배열이면)
			if (i == 0) {
				// 0에서 middle까지 반복
				for (int j = 0; j < middle; j++) {
					// j가 2차원 배열의 첫번째 배열의 크기보다 작으면 2차원 배열의 첫번째 배열의 j자리에 있는 정수,
					// j가 2차원 배열의 첫번째 배열의 크기보다 크면 middle을
					// answer의 j자리에 대입
					answer[j] = (j < twoDimentionList[i].length) ? twoDimentionList[i][j] : middle;
				}
			// i가 1이면 (2차원 배열의 두번째 배열이면)
			} else {
				// middle에서 number까지 반복
				for (int j = middle; j < number; j++) {
					// j가 2차원 배열의 두번째 배열의 j - twoDimentionList[i].length - 1자리에 있는 정수를 answer의 j자리에 대입
					answer[j] = twoDimentionList[i][j - twoDimentionList[i].length - 1];
				}
			}
		}
		return answer;
	}
	
	
//	9. 사용자가 입력한 값이 배열에 있는지 검색하여 있으면 “OOO 치킨 배달 가능“, 없으면 “OOO 치킨은 없는 메뉴입니다“를 출력하세요. 단, 치킨 메뉴가 들어가있는 배열은 본인 스스로 정하세요.
	String problem9(String menu, String item) {
		// 입력한 menu에서 공백을 없에고 문자열배열로 변경해서 menuList에 대
		String[] menuList = menu.replaceAll("\\s", "").split(",");
		// 원하는 item에서 공백을 없에고 tempItem에 대입
		String tempItem = item.replaceAll("\\s", "");
		// menuList에 들어있는 menuIte들을 for문으로 본다
		for (String menuItem : menuList) {
			// menuItem이 tempItem과 같으변 배달 가능
			if (menuItem.equalsIgnoreCase(tempItem)) {
				return item + " 배달 가능";
			}
		}
		// menuItem에 tempItem이 없다
		return item + "은 없는 메뉴입니다";
	}
	
//	10. 주민등록번호 성별자리 이후부터 *로 가리고 출력하세요. 단, 원본 배열 값은 변경 없이 배열 복사본으로 변경하세요.
	String problem10() {
		// 주민등록번호 상수
		String REGISTRATION_NUMBER = "123456-1234567";
		// 주민등록번호 성별자리 이후부터 *로 가린 문자열을 저장할 변수
		String censoredRegistrationNumber = "";
		// '-' 이후인지 여부하는 불린
		boolean didPassDash = false;
		// '-' 오른쪽 숫자를 가리지 않게 "-" 이후로 몇번 반복했는지 저장할 변수
		int count = 0;
		// 0에서 REGISTRATION_NUMBER 기리까지 반복
		for (int i = 0; i < REGISTRATION_NUMBER.length(); i++) {
			// "-"와 "-" 오른쪽 숫자 이후면 censoredRegistrationNumber에  "*"를 더한다
			if (didPassDash && count > 0) {
				censoredRegistrationNumber += "*";
			// 아니면
			} else {
				// REGISTRATION_NUMBER의 i자리가 '-'면 didPassDash를 true로 대입
				if (REGISTRATION_NUMBER.charAt(i) == '-') {
					didPassDash = true;
				// 위에조건이 아닌대 '-' 이후이면 count에 1을 더한다
				} else if (didPassDash) {
					count += 1;
				}
				// censoredRegistrationNumber에 REGISTRATION_NUMBER의 i자리에 있는 char을 더한다
				censoredRegistrationNumber += REGISTRATION_NUMBER.charAt(i);
			}
		}
		return censoredRegistrationNumber;
	}
	
//	11. 10개의 값을 저장할 수 있는 정수형 배열을 선언 및 할당하고 1~10 사이의 난수를 발생시켜 배열에 초기화한 후 출력하세요.
	int[] problem11() {
		// 난수들을 저장할 10자리 배열 생성
		int[] answer = new int[10];
		// 0에서 10까지 반복
		for (int i = 0; i < 10; i++) {
			// 난수를 answer의 i자리에 대입
			answer[i] = ThreadLocalRandom.current().nextInt(10);
		}
		return answer;
	}
	
//	12. 10개의 값을 저장할 수 있는 정수형 배열을 선언 및 할당하고 1~10 사이의 난수를 발생시켜 배열에 초기화 후 배열 전체 값과 그 값 중에서 최대값과 최소값을 출력하세요.
	String problem12() {
		// 난수배열 생선
		int[] list = problem11();
		// 최대값을 저장할 변수
		int biggest = 0;
		// 최소값을 저장할 변수
		int smallest = 0;
				
		// 0에서 list크기까지 반복
		for (int i = 0; i < list.length; i++) {
			// list의 i값이 smallest보다 작으면 smallest에 list의 i값을 대입
			if (list[i] < smallest) {
				smallest = list[i]; 
			}
			// list의 i값이 biggest보다 크면 biggest에 list의 i값을 대입
			if (list[i] > biggest) {
				biggest = list[i]; 
			}
		}
		return "List: " + Arrays.toString(list) + " Biggest: " + biggest + " Smallest: " + smallest;
	}

//	13. 10개의 값을 저장할 수 있는 정수형 배열을 선언 및 할당하고 1~10 사이의 난수를 발생시켜 중복된 값이 없게 배열에 초기화한 후 출력하세요.
	int[] problem13(int listLength, int rangeMin, int rangeMax) {
		// answer의 몇번째 index까지 대입을 했는지 저장하는 변수
		int count = 0;
		// 난수를 저장할 변수
		int randomNumber;
		// 난수들을 저장할 정수배열
		int[] answer = new int[listLength];
		// 중복된 값인지 여부하는 불린
		boolean isDuplicate;
		
		// count가 listLength보다 작으면 반복
		while (count < listLength) {
			// 난수 생성
			randomNumber = ThreadLocalRandom.current().nextInt(rangeMin, rangeMax);
			// isDuplicate에 false대입 (아직 중복된 값인지 몰으니까)
			isDuplicate = false;
			// 0에서 count + 1까지 반복
			for (int i = 0; i < (count + 1); i++) {
				// answer의 i값이 randomNumber과 같으면 isDuplicate에 true대입하고 for문 break
				if (answer[i] == randomNumber) {
					isDuplicate = true;
					break;
				}
			}
			// isDuplicate이 false면 answer의 count자리에 randomNumber 대입후 count에 1을 더한다
			if (!isDuplicate) {
				answer[count] = randomNumber;
				count += 1;
			}
		}
		return answer;
	}
	
//	14. 로또 번호 자동 생성기 프로그램을 작성하는데 중복 값 없이 오름차순으로 정렬하여 출력하세요. 6 numbers total. lottery number range 1-49
	int[] problem14() {
		// 6개 길이의 난수배열 생성. 값들은 1-50난수들
		int[] listToSort = problem13(6, 1, 50);
		// bubbleSort
		int[] sortedList = bubbleSort(listToSort);
		return sortedList;
	}
	
//	15. 문자열을 입력 받아 문자열에 어떤 문자가 들어갔는지 배열에 저장하고 문자의 개수와 함께 출력하세요.
	String problem15(String stringToConvert) {
		// 문자들을 저장할 문자열변수
		String answer = "";
		// answer을 배열로 변경하고 저장할 배열
		char[] charList;
		// stringToConvert에 있는 값들을 본다
		for (char letter : stringToConvert.toCharArray()) {
			// letter가 answer에 없으면 answer에 더한다
			if (!isInString(answer, letter)) {
				answer += letter;
			}
		}
		// answer를 배열로 변경하고 charList에 대입
		charList = answer.toCharArray();
		return "Chars: " + Arrays.toString(charList) + " Char count: " + charList.length; 
	}
	
//	16. 사용자가 입력한 배열의 길이만큼의 문자열 배열을 선언 및 할당하고 배열의 인덱스에 넣을 값 역시 사용자가 입력하여 초기화 하세요. 단, 사용자에게 배열에 값을 더 넣을지 물어보고 몇 개를 더 입력할 건지, 늘린 곳에 어떤 데이터를 넣을 것인지 받으세요. 사용자가 더 이상 입력하지 않겠다고 하면 배열 전체 값을 출력하세요.
	String[] problem16() {
		// 입력된 문자열을 배열로 변경하기 위한 도우미 문자열
		String answer = "";
		// 입력된 문자열을 저장할 변수
		String stringToAdd;
		// 입력한 문자열들을 저장할 배열 
		String[] answerList;
		// 사용자가 더 입력을 하고 싶은지 여부하는 불린
		boolean shouldContinue = true;
		// 처음으로 while문을 반복했는지 여부하는 불린 (","를 answer에 더할지 말지에 사용)
		boolean isFirstLoop = true;
		// 입력받을 문자열 휫수를 저장할 변수
		int count;
		// shouldContinue가 true이면 반복
		while (shouldContinue) {
			System.out.print("Please enter the number of strings you'd like to enter for problem 16: ");
			// 입력받을 문자열 휫수 대입
			count = customParseInt();
			// 0에서 count까지 반복
			for (int i = 0; i < count; i++) {
				System.out.print("enter a string: ");
				// stringToAdd에 i가 0이고 isFirstLoop면 입력한 문자열만, 아니면 ", "와 같이 대입
				stringToAdd = i == 0 && isFirstLoop ? scanner.nextLine() : ", " + scanner.nextLine();;
				// answer에 stringToAdd 더하고 answer에 대입
				answer += stringToAdd;
			}
			// 사용자가 더 입력을 하고 싶은지 shouldContinue에 대입
			shouldContinue = handleYesOrNoError();
			// 반복을 했으니까 isFirstLoop에 false 대입
			isFirstLoop = false;
		}
		// answer을 각 "," 다음에 단어로 나누어서 생성한 배열 대입
		answerList = answer.split(",");
		return answerList;
	}
	
	String[] problem16v2() {
		// 입력한 문자열들을 저장할 배열 
		String[] answer = new String[0];
		// 이전에 입력한 문자열들과 새롭게 입력한 문자열들을 저장할 배열 
		String[] newAnswer;
		// 새롭게 입력한 문자열들을 저장할 배열
		String[] stringsToAdd;
		// 사용자가 더 입력을 하고 싶은지 여부하는 불린
		boolean shouldContinue = true;
		// 입력받을 문자열 휫수를 저장할 변수
		int count;
		// shouldContinue가 true이면 반복
		while (shouldContinue) {
			System.out.print("Please enter the number of strings you'd like to enter for problem 16: ");
			// 입력받을 문자열 휫수 대입
			count = customParseInt();
			// count크기의 문자열배열 생성하고 대입
			stringsToAdd = new String[count];
			// 0에서 count까지 반복
			for (int i = 0; i < count; i++) {
				System.out.print("enter a string: ");
				// stringsToAdd의 i자리에 입력한 문자열 저장
				stringsToAdd[i] = scanner.nextLine(); 
			}
			// answer 크기 + count크기의 문자열배열 생성하고 대입 
			newAnswer = new String[answer.length + count];
			// 0에서 answer 크기까지 반복
			for (int i = 0; i < answer.length; i++) {
				// newAnswer의 i자리에 answer의 i값 저장
				newAnswer[i] = answer[i];
			}
			// answer 크기에서 answer 크기 + count크기까지 반복
			for (int i = answer.length; i < (answer.length + count); i++) {
				// newAnswer의 i자리에 stringsToAdd의 i - answer.length값 저장
				newAnswer[i] = stringsToAdd[i - answer.length];
			}
			// answer에 newAnswer 대입
			answer = newAnswer;
			// 사용자가 더 입력을 하고 싶은지 shouldContinue에 대입
			shouldContinue = handleYesOrNoError();
		}
		return answer;
	}
	
	void problem16v3() {
		String[][] wordsToAdd = new String[10][];
		String[] words;
		String answer;
		int count = 0;
		int numberOfWordsEntered = 0;
		int numberOfTimesEntered = 0;
		boolean isNumber = false;
		boolean isYesOrNo = false;
		for (int i = 0; i < wordsToAdd.length; i++) {
			do {
				System.out.print("숫자를 입력해주새요: ");
				try {
					count = Integer.parseInt(scanner.nextLine());
					isNumber = true;
					numberOfWordsEntered += count;
				} catch (NumberFormatException e) {
					System.out.println(e.getMessage());
				}
			} while (!isNumber);
			isNumber = false;
			wordsToAdd[i] = new String[count];
			for (int j = 0; j < count; j++) {
				System.out.print("문자를 입력해주새요: ");
				wordsToAdd[i][j] = scanner.nextLine();
			}
			do {
				System.out.print("더 값을 입력하시겠습니까?(Y/N): ");
				answer = scanner.nextLine();
				if (answer.toLowerCase().charAt(0) == 'y' || answer.toLowerCase().charAt(0) == 'n') {
					isYesOrNo = true;
					numberOfTimesEntered += 1;
				} else {
					System.out.println("y 아니면 n을 입력하세요");
				}
			} while (!isYesOrNo);
			isYesOrNo = false;
			if (answer.toLowerCase().charAt(0) == 'n') {
				break;
			}
		}
		words = new String[numberOfWordsEntered];
		count = 0;
		for (int i = 0; i < numberOfTimesEntered; i++) {
			for (int j = 0; j < wordsToAdd[i].length; j++) {
				words[count] = wordsToAdd[i][j];
				count++;
			}
		}
		
		System.out.println(Arrays.toString(words));
	}
	
	public static void main(String[] args) {
		ArrayPracticeProblems problems = new ArrayPracticeProblems();
		problems.startProgram();
	}

}

