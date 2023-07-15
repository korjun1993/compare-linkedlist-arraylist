package org.example;

import java.util.ArrayList;
import java.util.List;

import org.example.domain.Board;
import org.example.repository.BoardRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SaveDummyDataRunner implements CommandLineRunner {

	private final BoardRepository boardRepository;

	// application 이 시작되는 시점에 수행되는 로직
	// n 개의 가짜 데이터를 생성하여 DB에 저장
	@Override
	public void run(String... args) {
		int dataSize = 10000000;
		int batchSize = 10000;
		boardRepository.saveAll(dummyData(dataSize), batchSize);
	}

	public List<Board> dummyData(int n) {
		List<Board> boards = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			Board board = Board.builder()
				.title(i + "번째 글의 제목입니다")
				.description(i + "번째 글의 내용입니다")
				.author("작성자" + i)
				.build();
			boards.add(board);
		}

		return boards;
	}
}
