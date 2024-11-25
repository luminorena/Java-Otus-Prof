package ru.flamexander.db.interaction.lesson;

@RepositoryTable(title = "migration_history")
public class MigrationHistory {
    @RepositoryIdField
    private Long id;
    @RepositoryField
    private String query_text;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuery_text() {
        return query_text;
    }

    public void setQuery_text(String query_text) {
        this.query_text = query_text;
    }
}
