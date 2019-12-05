package fr.bovoyages.restBack;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.hash.Hashing;

import fr.bovoyages.dao.CommercialRepository;
import fr.bovoyages.dao.DatesVoyageRepository;
import fr.bovoyages.dao.DestinationRepository;
import fr.bovoyages.dao.ImageRepository;
import fr.bovoyages.dto.DatesVoyageDTO;
import fr.bovoyages.dto.DestinationDTO;
import fr.bovoyages.entities.Commercial;
import fr.bovoyages.entities.DatesVoyage;
import fr.bovoyages.entities.Destination;
import fr.bovoyages.entities.Image;
import fr.bovoyages.images.*;

@Controller
public class BoVoyagesRestBack {
	@Autowired
	CommercialRepository comRepo;
	@Autowired
	DestinationRepository destiRepo;
	@Autowired
	DatesVoyageRepository datesRepo;
	@Autowired
	StorageService storageService;
	@Autowired
	ImageRepository imageRepo;

	@GetMapping("/backToDestinations")
	public String getBacktoDestinationsPage(Model model) {
		List<Destination> dest = destiRepo.findAll(Sort.by(Sort.Direction.ASC, "region"));
		List<Destination> destinations = new ArrayList<Destination>();
		for (Destination d : dest) {
			if (d.getDeleted() == 0) {
				destinations.add(d);
			}
		}
		model.addAttribute("destinations", destinations);
		return "destinations";
	}
	
	@GetMapping("/getDestinationsByRegionStartingWith")
	public String getDestinationsByRegionStartingWith(Model model, @RequestParam("search") String search) {
		List<Destination> dest = destiRepo.getByRegionStartingWithOrderByRegion(search);
		List<Destination> destinations = new ArrayList<Destination>();
		for (Destination d : dest) {
			if (d.getDeleted() == 0) {
				destinations.add(d);
			}
		}
		model.addAttribute("destinations", destinations);
		return "destinations";
	}

	@GetMapping("/signin")
	public String signin(Model model) {
		Commercial commercial = new Commercial();
		model.addAttribute("commercial", commercial);
		return "signin";
	}

	@PostMapping("/connexion")
	public String connexion(@RequestParam String password, Commercial commercial, Model model) {
		String redirectToHtmlPage = "";

		if (comRepo.getByUsername(commercial.getUsername()).isPresent()) {
			String salt = comRepo.getCommercialSalt(commercial.getUsername());
			String toCalculate = salt + password;
			String calculatedHashPlusSalt = Hashing.sha256().hashString(toCalculate, StandardCharsets.UTF_8).toString();
			String baseHashAndSalt = comRepo.getCommercialSaltedHash(commercial.getUsername());
			if (calculatedHashPlusSalt.equals(baseHashAndSalt)) {
				model.addAttribute("commercial", commercial);
				List<Destination> dest = destiRepo.findAll(Sort.by(Sort.Direction.ASC, "region"));
				List<Destination> destinations = new ArrayList<Destination>();
				for (Destination d : dest) {
					if (d.getDeleted() == 0) {
						destinations.add(d);
					}
				}
				model.addAttribute("destinations", destinations);
				redirectToHtmlPage = "destinations";
			} else {
				redirectToHtmlPage = "signin";
			}
		}
		return redirectToHtmlPage;
	}

	@PostMapping("/addCommercial")
	public String addCommercial(String username, String password) {
		String msg = "";
		Commercial com = new Commercial(username);
		comRepo.save(com);
		String salt = String.valueOf(Math.random());
		String toCalculate = salt + password;
		String calculatedHashPlusSalt = Hashing.sha256().hashString(toCalculate, StandardCharsets.UTF_8).toString();
		comRepo.saveCommercialSaltAndSaltedHash(com.getUsername(), salt, calculatedHashPlusSalt);
		return msg;
	}

	@PostMapping("/destinationDetails")
	public String getDestinationDetails(@RequestParam long id, Model model) {
		Destination destination = destiRepo.findById(id).get();
		model.addAttribute("destination", destination);
		List<DatesVoyage> datess = destination.getDates();
		List<DatesVoyage> dates = new ArrayList<DatesVoyage>();
		for (DatesVoyage d : datess) {
			if (d.getDeleted() == 0) {
				dates.add(d);
			}
		}
		model.addAttribute("dates", dates);
		if(destination.getImages().isEmpty()==false) {
			String image = destination.getImages().get(0).getImage();
			String src = "..static/images/" + image;
			String thsrc = "images/" + image;
			model.addAttribute("src", src);
			model.addAttribute("thsrc", thsrc);
		}
		return "destinationDetails";
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////BACK/////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@PostMapping("/destinationNew")
	public String addDestination() {
		return "newDestination";
	}

	@PostMapping("/destinationCreate")
	public String createDestination(@RequestParam("region") String region,
			@RequestParam("description") String description, Model model) {
		Destination newDest = new Destination(region, description);
		newDest.setDeleted(0);
		destiRepo.save(newDest);
		return this.getBacktoDestinationsPage(model);
	}

	@PostMapping("/destinationUpdateDescription")
	public String updateDestinationDescription(@RequestParam("idDest") long idDest,
			@RequestParam("description") String newDescription, Model model) {
		Destination destination = destiRepo.findById(idDest).get();
		destination.setDescription(newDescription);
		destiRepo.save(destination);
		return this.getDestinationDetails(idDest, model);
	}

//	@PostMapping("destination/update")
//	public String updateDestination(@RequestBody Destination destination) {
//		long oldId = destination.getId();
//		destiRepo.delete(destiRepo.findById(oldId).get());
//		destination.setId(oldId);
//		destiRepo.save(destination);
//		return new DestinationDTO(destination);
//	}

	@PostMapping("/destinationDelete")
	public String deleteDestination(@RequestParam("id") long id, Model model) {
		Destination destination = destiRepo.findById(id).get();
		destination.setDeleted(1);
		destiRepo.save(destination);
		return this.getBacktoDestinationsPage(model);
	}

	@PostMapping("/destinationDateNew")
	public String addDatesVoyagesToDestination(@RequestParam("id") long id,
			@RequestParam("newDateAller") String newDateAller, @RequestParam("newDateRetour") String newDateRetour,
			@RequestParam("newPrixHT") float newPrixHT, @RequestParam("newPromo") float newPromo,
			@RequestParam("newNbrePlaces") int newNbrePlaces, Model model) throws ParseException {
		SimpleDateFormat franck = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		Destination dest = destiRepo.findById(id).get();
		List<DatesVoyage> listeDates = dest.getDates();
		Date newAller = franck.parse(newDateAller);
		Date newRetour = franck.parse(newDateRetour);
		DatesVoyage newDate = new DatesVoyage();
		newDate.setDateAller(newAller);
		newDate.setDateRetour(newRetour);
		newDate.setDeleted(0);
		newDate.setNbrePlaces(newNbrePlaces);
		newDate.setPrixHT(newPrixHT);
		newDate.setPromo(newPromo);
		listeDates.add(newDate);
		dest.setDates(listeDates);
		destiRepo.save(dest);
		return this.getDestinationDetails(id, model);
	}

	@PostMapping("/destinationDateUpdate")
	public String updateDatesVoyagesToDestination(@RequestParam("idDest") long idDest,
			@RequestParam("idDate") long idDate, @RequestParam("dateAller") String newDateAller,
			@RequestParam("dateRetour") String newDateRetour, @RequestParam("prixHT") float newPrixHT,
			@RequestParam("promo") float newPromo, @RequestParam("nbrePlaces") int newNbrePlaces, Model model)
			throws ParseException {
		SimpleDateFormat franck = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		Destination dest = destiRepo.findById(idDest).get();
		DatesVoyage newDate = datesRepo.findById(idDate).get();
		Date newAller = franck.parse(newDateAller);
		Date newRetour = franck.parse(newDateRetour);
		newDate.setDateAller(newAller);
		newDate.setDateRetour(newRetour);
		newDate.setDeleted(0);
		newDate.setNbrePlaces(newNbrePlaces);
		newDate.setPrixHT(newPrixHT);
		newDate.setPromo(newPromo);
		destiRepo.save(dest);

		return this.getDestinationDetails(idDest, model);
	}

	@PostMapping("/destinationDateDelete")
	public String deleteDatesVoyagesToDestination(@RequestParam("idDest") long idDest,
			@RequestParam("idDate") long idDate, Model model) {
		Destination dest = destiRepo.findById(idDest).get();
		List<DatesVoyage> listeDates = dest.getDates();
		DatesVoyage dateToErase = datesRepo.findById(idDate).get();
		datesRepo.delete(dateToErase);
		listeDates.remove(dateToErase);
		dest.setDates(listeDates);
		destiRepo.save(dest);
		return this.getDestinationDetails(idDest, model);
	}

	@PostMapping("/images")
	public String listUploadedFiles(Model model, @RequestParam("idDest") long idDest) throws IOException {

		model.addAttribute("files",
				storageService.loadAll()
						.map(path -> MvcUriComponentsBuilder
								.fromMethodName(BoVoyagesRestBack.class, "serveFile", path.getFileName().toString())
								.build().toString())
						.collect(Collectors.toList()));
model.addAttribute("idDest", idDest);
		return "uploadForm";
	}

	@GetMapping("/files/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

		Resource file = storageService.loadAsResource(filename);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}

	@PostMapping("/images2")
	public String handleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes,
			@RequestParam("idDest") long idDest, Model model) {
		storageService.store(file);
		redirectAttributes.addFlashAttribute("message",
				"You successfully uploaded " + file.getOriginalFilename() + "!");
		String imageName = file.getOriginalFilename();
		Destination destination=destiRepo.findById(idDest).get();
		List<Image> listeImages = destination.getImages();
		Image image = new Image(imageName, idDest);
		listeImages.add(image);
		destination.setImages(listeImages);
		destiRepo.save(destination);
		imageRepo.save(image);

		return getDestinationDetails(idDest, model);
	}

	@ExceptionHandler(StorageFileNotFoundException.class)
	public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
		return ResponseEntity.notFound().build();
	}

}
