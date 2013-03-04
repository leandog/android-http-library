package com.leandog.http;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

import java.io.File;

import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.leandog.http.FileRequestContent;

@RunWith(Enclosed.class)
public class FileRequestContentTest {

    public static class WhenWorkingWithPhotoFileRequests {
        @Mock
        MultipartEntity entity;

        private File file = new File("/data/foo/whatever.jPg");
        private FileRequestContent fileRequestContent = new FileRequestContent(file, "VehicleLicensePlatePhoto.jpg");

        @Before
        public void setUp() {
            MockitoAnnotations.initMocks(this);
        }

        @Test
        public void itPacakagesTheLicensePhotoWithNamePhoto() {
            fileRequestContent.configureEntity(entity);
            verify(entity).addPart(eq("photo"), any(FileBody.class));
        }

        @Test
        public void itAddsTheFileToTheFormData() {
            fileRequestContent.configureEntity(entity);
            assertEquals(file, capturedFileBody().getFile());
        }

        @Test
        public void itUsesVehicleLicensePlatePhotoJpgForPhotoName() {
            fileRequestContent.configureEntity(entity);
            assertEquals("VehicleLicensePlatePhoto.jpg", capturedFileBody().getFilename());
        }
        
        @Test
        public void itUsesTheMimeTypeApplicationOctetStream() {
            fileRequestContent.configureEntity(entity);
            assertEquals("image/jpeg", capturedFileBody().getMimeType());
        }

        private FileBody capturedFileBody() {
            ArgumentCaptor<FileBody> fileBody = ArgumentCaptor.forClass(FileBody.class);
            verify(entity).addPart(anyString(), fileBody.capture());
            return fileBody.getValue();
        }
    }

    public static class WhenWorkingWithAudioFileRequests {
        
        @Mock
        MultipartEntity entity;

        private File file = new File("/data/foo/whatever.wAv");
        private FileRequestContent fileRequestContent = new FileRequestContent(file, "LossDescriptionVoiceMemo.wav");

        @Before
        public void setUp() {
            MockitoAnnotations.initMocks(this);
        }
        
        @Test
        public void itPackagesTheVoiceMemoWithTheNameVoiceMemo() {
            fileRequestContent.configureEntity(entity);
            verify(entity).addPart(eq("voiceMemo"), any(FileBody.class));
        }

        @Test
        public void itUsesLossDescriptionVoiceMemoWavForVoiceMemoName() {
            fileRequestContent.configureEntity(entity);
            assertEquals("LossDescriptionVoiceMemo.wav", capturedFileBody().getFilename());
        }
        
        @Test
        public void itUsesTheMimeTypeApplicationOctetStream() {
            fileRequestContent.configureEntity(entity);
            assertEquals("application/octet-stream", capturedFileBody().getMimeType());
        }

        private FileBody capturedFileBody() {
            ArgumentCaptor<FileBody> fileBody = ArgumentCaptor.forClass(FileBody.class);
            verify(entity).addPart(anyString(), fileBody.capture());
            return fileBody.getValue();
        }
    }

}
