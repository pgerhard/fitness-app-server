package de.university.reutlingen.mobile.computing.fitnessappserver.util;

import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.impl.TimeBasedGenerator;
import org.springframework.util.IdGenerator;

import java.util.UUID;

/**
 * ID generator for UUIDs.
 */
public final class UuidGenerator implements IdGenerator {

  private final TimeBasedGenerator generator = Generators.timeBasedGenerator();

  @Override
  public UUID generateId() {
    return generator.generate();
  }
}