package com.gmatieso.tags.repository;

import com.gmatieso.tags.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
}
