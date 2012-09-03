/*
 * Copyright 2012 JBoss Inc
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.uberfire.annotations.processors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;

import org.junit.Test;

/**
 * Tests for Pop-up related class generation
 */
public class PerspectiveProcessorTest extends AbstractProcessorTest {

    @Test
    public void testNoPerspectiveAnnotation() throws FileNotFoundException {
        final Result result = new Result();
        final List<Diagnostic< ? extends JavaFileObject>> diagnostics = compile( new PerspectiveProcessor( new GenerationCompleteCallback() {

                                                                                     @Override
                                                                                     public void generationComplete(String code) {
                                                                                         result.setActualCode( code );
                                                                                     }
                                                                                 } ),
                                                                                 "org/uberfire/annotations/processors/PerspectiveTest1" );
        assertSuccessfulCompilation( diagnostics );
        assertNull( result.getActualCode() );
    }

    @Test
    public void testIncorrectReturnTypeWithoutArguments() throws FileNotFoundException {
        final Result result = new Result();
        final List<Diagnostic< ? extends JavaFileObject>> diagnostics = compile( new PerspectiveProcessor( new GenerationCompleteCallback() {

                                                                                     @Override
                                                                                     public void generationComplete(String code) {
                                                                                         result.setActualCode( code );
                                                                                     }
                                                                                 } ),
                                                                                 "org/uberfire/annotations/processors/PerspectiveTest2" );
        assertSuccessfulCompilation( diagnostics );
        assertNull( result.getActualCode() );
    }

    @Test
    public void testCorrectReturnTypeWithArguments() throws FileNotFoundException {
        final Result result = new Result();
        final List<Diagnostic< ? extends JavaFileObject>> diagnostics = compile( new PerspectiveProcessor( new GenerationCompleteCallback() {

                                                                                     @Override
                                                                                     public void generationComplete(String code) {
                                                                                         result.setActualCode( code );
                                                                                     }
                                                                                 } ),
                                                                                 "org/uberfire/annotations/processors/PerspectiveTest3" );
        assertSuccessfulCompilation( diagnostics );
        assertNull( result.getActualCode() );
    }

    @Test
    public void testCorrectReturnTypeWithoutArguments() throws FileNotFoundException {
        final String pathCompilationUnit = "org/uberfire/annotations/processors/PerspectiveTest4";
        final String pathExpectedResult = "org/uberfire/annotations/processors/expected/PerspectiveTest4.expected";

        final Result result = new Result();
        result.setExpectedCode( getExpectedSourceCode( pathExpectedResult ) );

        final List<Diagnostic< ? extends JavaFileObject>> diagnostics = compile( new PerspectiveProcessor( new GenerationCompleteCallback() {

                                                                                     @Override
                                                                                     public void generationComplete(String code) {
                                                                                         result.setActualCode( code );
                                                                                     }
                                                                                 } ),
                                                                                 pathCompilationUnit );
        assertSuccessfulCompilation( diagnostics );
        assertNotNull( result.getActualCode() );
        assertNotNull( result.getExpectedCode() );
        assertEquals( result.getActualCode(),
                      result.getExpectedCode() );
    }

    @Test
    public void testCorrectReturnTypeWithoutArgumentsIsDefault() throws FileNotFoundException {
        final String pathCompilationUnit = "org/uberfire/annotations/processors/PerspectiveTest5";
        final String pathExpectedResult = "org/uberfire/annotations/processors/expected/PerspectiveTest5.expected";

        final Result result = new Result();
        result.setExpectedCode( getExpectedSourceCode( pathExpectedResult ) );

        final List<Diagnostic< ? extends JavaFileObject>> diagnostics = compile( new PerspectiveProcessor( new GenerationCompleteCallback() {

                                                                                     @Override
                                                                                     public void generationComplete(String code) {
                                                                                         result.setActualCode( code );
                                                                                     }
                                                                                 } ),
                                                                                 pathCompilationUnit );
        assertSuccessfulCompilation( diagnostics );
        assertNotNull( result.getActualCode() );
        assertNotNull( result.getExpectedCode() );
        assertEquals( result.getActualCode(),
                      result.getExpectedCode() );
    }

    @Test
    public void testMultipleAllCorrect() throws FileNotFoundException {
        final String pathCompilationUnit = "org/uberfire/annotations/processors/PerspectiveTest6";
        final String pathExpectedResult0 = "org/uberfire/annotations/processors/expected/PerspectiveTest6-0.expected";
        final String pathExpectedResult1 = "org/uberfire/annotations/processors/expected/PerspectiveTest6-1.expected";

        final Result result0 = new Result();
        result0.setExpectedCode( getExpectedSourceCode( pathExpectedResult0 ) );

        final Result result1 = new Result();
        result1.setExpectedCode( getExpectedSourceCode( pathExpectedResult1 ) );

        final List<Result> results = new ArrayList<Result>();
        results.add( result0 );
        results.add( result1 );

        final List<Diagnostic< ? extends JavaFileObject>> diagnostics = compile( new PerspectiveProcessor( new GenerationCompleteCallback() {

                                                                                     int resultIndex = 0;

                                                                                     @Override
                                                                                     public void generationComplete(String code) {
                                                                                         results.get( resultIndex ).setActualCode( code );
                                                                                         resultIndex++;
                                                                                     }
                                                                                 } ),
                                                                                 pathCompilationUnit );
        assertSuccessfulCompilation( diagnostics );
        assertNotNull( result0.getActualCode() );
        assertNotNull( result0.getExpectedCode() );
        assertEquals( result0.getActualCode(),
                      result0.getExpectedCode() );
        assertNotNull( result1.getActualCode() );
        assertNotNull( result1.getExpectedCode() );
        assertEquals( result1.getActualCode(),
                      result1.getExpectedCode() );
    }

    @Test
    public void testMultipleOneCorrectOneIncorrect() throws FileNotFoundException {
        final String pathCompilationUnit = "org/uberfire/annotations/processors/PerspectiveTest7";
        final String pathExpectedResult0 = "org/uberfire/annotations/processors/expected/PerspectiveTest7-0.expected";

        final Result result0 = new Result();
        result0.setExpectedCode( getExpectedSourceCode( pathExpectedResult0 ) );

        final Result result1 = new Result();

        final List<Result> results = new ArrayList<Result>();
        results.add( result0 );
        results.add( result1 );

        final List<Diagnostic< ? extends JavaFileObject>> diagnostics = compile( new PerspectiveProcessor( new GenerationCompleteCallback() {

                                                                                     int resultIndex = 0;

                                                                                     @Override
                                                                                     public void generationComplete(String code) {
                                                                                         results.get( resultIndex ).setActualCode( code );
                                                                                         resultIndex++;
                                                                                     }
                                                                                 } ),
                                                                                 pathCompilationUnit );
        assertSuccessfulCompilation( diagnostics );
        assertNotNull( result0.getActualCode() );
        assertNotNull( result0.getExpectedCode() );
        assertEquals( result0.getActualCode(),
                      result0.getExpectedCode() );
        assertNull( result1.getActualCode() );
        assertNull( result1.getExpectedCode() );
    }

}