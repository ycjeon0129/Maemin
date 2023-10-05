package com.tft.userservice.user.controller;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.tft.userservice.jwt.dto.Result;
import com.tft.userservice.user.dto.request.BillAddReq;
import com.tft.userservice.user.dto.request.IdCheckReq;
import com.tft.userservice.user.dto.request.JoinReq;
import com.tft.userservice.user.dto.response.BillAddRes;
import com.tft.userservice.user.dto.response.BillRes;
import com.tft.userservice.user.dto.response.IdCheckRes;
import com.tft.userservice.user.service.TestService;
import com.tft.userservice.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/owners")
public class OwnerController {

    private final UserService userService;

    @PutMapping("/add-store/{storeId}")
    public ResponseEntity<Result> joinPay(@RequestHeader(value = "user-id") String userId,
                                          @PathVariable Long storeId) {
        userService.addStore(userId, storeId);
        return ResponseEntity.ok(Result.createSuccessResult(storeId + "store 추가 성공"));
    }

    @GetMapping("/qr/{storeId}")
    public ResponseEntity<byte[]> qrToTistory(@PathVariable Long storeId) throws WriterException, IOException {
        // QR 정보
        int width = 200;
        int height = 200;
        String url = "https://j9c208.p.ssafy.io/customer/store-detail/" + storeId;
        log.info("주소 : {}", url);

        // QR Code - BitMatrix: qr code 정보 생성
        BitMatrix encode = new MultiFormatWriter()
                .encode(url, BarcodeFormat.QR_CODE, width, height);

        // QR Code - Image 생성. : 1회성으로 생성해야 하기 때문에
        // stream으로 Generate(1회성이 아니면 File로 작성 가능.)
        try {
            //output Stream
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            //Bitmatrix, file.format, outputStream
            MatrixToImageWriter.writeToStream(encode, "PNG", out);

            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_PNG)
                    .body(out.toByteArray());

        }catch (Exception e){log.warn("QR Code OutputStream 도중 Excpetion 발생, {}", e.getMessage());}

        return null;
    }




}
