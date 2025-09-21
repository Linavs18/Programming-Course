package co.edu.sena.notes.repository;

import co.edu.sena.notes.model.Note;
import co.edu.sena.notes.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findByUserId(Long userId);

    List<Note> findByUser(User user);
}
