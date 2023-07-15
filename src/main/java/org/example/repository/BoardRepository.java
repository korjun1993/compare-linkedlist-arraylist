package org.example.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.example.domain.Board;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Lists;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
@RequiredArgsConstructor
public class BoardRepository {

	private final JdbcTemplate jdbcTemplate;

	public void saveAll(List<Board> boards, int n) {
		List<List<Board>> partition = Lists.partition(boards, n);
		for (List<Board> subBoards : partition) { // type(subBoards) == type(boards)
			long startTime = System.currentTimeMillis();
			batchInsert(subBoards);
			long resultTime = System.currentTimeMillis() - startTime;
			log.info("{}ms가 소요됐습니다.", resultTime);
		}
	}

	public void batchInsert(List<Board> subBoards) {
		String sql = "INSERT INTO board (title, author, description) VALUES (?, ?, ?)";

		jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				Board board = subBoards.get(i);
				ps.setString(1, board.getTitle());
				ps.setString(2, board.getAuthor());
				ps.setString(3, board.getDescription());
			}

			@Override
			public int getBatchSize() {
				return subBoards.size();
			}
		});
	}
}
