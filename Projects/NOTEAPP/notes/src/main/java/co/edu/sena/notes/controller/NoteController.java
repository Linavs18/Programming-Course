package co.edu.sena.notes.controller;


import co.edu.sena.notes.model.Note;
import co.edu.sena.notes.model.User;
import co.edu.sena.notes.repository.NoteRepository;
import co.edu.sena.notes.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
public class NoteController {
    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private UserRepository userRepository;

    // Listar notas del usuario autenticado
    @GetMapping
    public ResponseEntity<List<Note>> getMyNotes(@AuthenticationPrincipal UserDetails userDetails) {
        User user =  userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return ResponseEntity.ok(noteRepository.findByUser(user));
    }

    // Crear nueva nota
    @PostMapping
    public ResponseEntity<Note> createNote(@RequestBody Note note, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        note.setUser(user);
        return ResponseEntity.ok(noteRepository.save(note));
    }

    // Actualizar una nota (solo si pertenece al usuario)
    @PutMapping("/{id}")
    public ResponseEntity<Note>updateNote(@PathVariable Long id, @RequestBody Note updated, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuario no encontradi"));
        return noteRepository.findById(id)
                .filter(note -> note.getUser().getId().equals(user.getId()))
                .map(note -> {
                    note.setTitle(updated.getTitle());
                    note.setContent(updated.getContent());
                    return ResponseEntity.ok(noteRepository.save(note));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    //Eliminar una nota
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable Long id,
                                           @AuthenticationPrincipal UserDetails userDetails) {

        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        return noteRepository.findById(id)
                .filter(note -> note.getUser().getId().equals(user.getId()))
                .<ResponseEntity<Void>>map(note -> {
                    noteRepository.delete(note);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
