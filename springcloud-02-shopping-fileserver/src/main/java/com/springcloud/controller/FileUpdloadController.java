package com.springcloud.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.springcloud.common.UploadUtils;
import com.springcloud.vo.ResultValue;

/**
 * 上传图片文件
 * 
 * @author 201607090134-王若恒
 *
 */
@RestController
public class FileUpdloadController {

	// 从application.properties文件中获得指定键的值，并赋给相应的成员变量
	@Value("${web.user-path}")
	private String userPath;

	@Value("${web.goods-path}")
	private String goodsPath;

	/**
	 * 上传用户头像
	 * 
	 * @param file
	 *            上传的用户头像
	 * @return
	 */
	@RequestMapping("/userUpload")
	public ResultValue userUpload(@RequestParam("userImage") MultipartFile file) {
		ResultValue rv = new ResultValue();
		// 未调用方法时的上传方式
		/*
		 * // 先获得新的文件名 String fileName = UploadUtils.getFileName(); //
		 * 根据上传文件的文件名获得文件的扩展名 String exendeName =
		 * UploadUtils.getExendedName(file.getOriginalFilename());
		 * 
		 * // 上传文件 try { // 1.将文件转换为字节类型的数组 byte[] bytes = file.getBytes(); //
		 * 2.创建File类的对象，并设置文件的上传路径及文件名 File saveFile = new File(userPath + fileName +
		 * exendeName); // 3.上传文件 FileCopyUtils.copy(bytes, saveFile);
		 * 
		 * // 上传成功返回0 rv.setCode(0); // 将文件新的文件名和扩展名传递回视图层 Map<String, Object> map = new
		 * HashMap<>(); map.put("fileName", fileName); map.put("exendeName",
		 * exendeName); rv.setDataMap(map);
		 * 
		 * return rv; } catch (IOException e) { e.printStackTrace(); }
		 */
		try {
			Map<String, Object> map = this.fileUpload(file, userPath);
			if (map != null && map.size() > 0) {
				rv.setCode(0);
				rv.setDataMap(map);

				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 失败返回1
		rv.setCode(1);
		rv.setMessage("用户头像上传失败！！！");

		return rv;
	}

	@RequestMapping(value = "/goodsUpload")
	public ResultValue goodsUpload(@RequestParam("goodsImage") MultipartFile file) {
		ResultValue rv = new ResultValue();

		try {
			Map<String, Object> map = this.fileUpload(file, goodsPath);
			if (map != null && map.size() > 0) {
				rv.setCode(0);
				rv.setDataMap(map);

				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 失败返回1
		rv.setCode(1);
		rv.setMessage("商品图片上传失败！！！");

		return rv;
	}

	/**
	 * 删除数据库中对应编号的商品图片
	 * 
	 * @param goodsImg
	 *            商品图片信息
	 * @return
	 */
	@RequestMapping(value = "/deleteGoodsImg")
	public ResultValue deleteGoodsImg(@RequestParam("goodsImg") String goodsImg) {
		ResultValue rv = new ResultValue();

		try {
			// 从url中获得商品图片的名字
			int indexOf = goodsImg.lastIndexOf("/");
			if (indexOf != -1) {
				String fileName = goodsImg.substring(indexOf + 1);
				// System.out.println(fileName);

				File file = new File(this.goodsPath + fileName);
				// 判断文件或目录是否存在
				file.delete();

				rv.setCode(0);
				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		return rv;
	}

	/**
	 * 删除数据库中对应编号的用户头像图片
	 * 
	 * @param userImage
	 *            用户头像图片信息
	 * @return
	 */
	@RequestMapping(value = "/deleteUserImg")
	public ResultValue deleteUserImg(@RequestParam("userImage") String userImage) {
		ResultValue rv = new ResultValue();

		try {
			// 从url中获得用户头像图片的名字
			int indexOf = userImage.lastIndexOf("/");
			if (indexOf != -1) {
				String fileName = userImage.substring(indexOf + 1);
				// System.out.println(fileName);

				File file = new File(this.userPath + fileName);
				// 判断文件或目录是否存在
				file.delete();

				rv.setCode(0);
				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		rv.setMessage("删除用户原头图片像失败！");
		return rv;
	}

	/**
	 * 上传文件
	 * 
	 * @param file
	 *            需要上传的文件
	 * @param path
	 *            上传文件的路径
	 * @return 成功返回java.util.Map实例，否则返回空
	 * @throws IOException
	 */
	private Map<String, Object> fileUpload(MultipartFile file, String path) throws IOException {
		Map<String, Object> map = null;

		// 先获得新的文件名
		String fileName = UploadUtils.getFileName();
		// 根据上传文件的文件名获得文件的扩展名
		String exendeName = UploadUtils.getExendedName(file.getOriginalFilename());

		// 1.将文件转换为字节类型的数组
		byte[] bytes = file.getBytes();
		// 2.创建File类的对象，并设置文件的上传路径及文件名
		File saveFile = new File(path + fileName + exendeName);
		// 3.上传文件
		FileCopyUtils.copy(bytes, saveFile);

		map = new HashMap<>();
		map.put("fileName", fileName);
		map.put("exendeName", exendeName);

		return map;
	}
}
