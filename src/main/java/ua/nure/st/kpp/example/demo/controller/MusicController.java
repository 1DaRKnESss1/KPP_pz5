package ua.nure.st.kpp.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ua.nure.st.kpp.example.demo.dao.DAOFactory;
import ua.nure.st.kpp.example.demo.dao.IDAO;
import ua.nure.st.kpp.example.demo.dao.TypeDAO;
import ua.nure.st.kpp.example.demo.entity.Music;
import ua.nure.st.kpp.example.demo.form.AddMusicForm;
import ua.nure.st.kpp.example.demo.form.DeleteMusicForm;
import ua.nure.st.kpp.example.demo.form.EditMusicForm;
import ua.nure.st.kpp.example.demo.form.GetMusicByComposerForm;

@Controller
public class MusicController {
    private final IDAO dao = DAOFactory.getDAOInstance(TypeDAO.MySQL);

    @RequestMapping(value = { "/", "/musics" }, method = { RequestMethod.GET })
    public String showAllMusic(Model model) {

        List<Music> list = dao.getAllMusics();

        model.addAttribute("allMusics", list);

        return "musicsPage";
    }

    @RequestMapping(value = "/addMusic", method = RequestMethod.GET)
    public String showAddMusicForm(Model model) {
        model.addAttribute("addMusicForm", new AddMusicForm());

        return "AddMusicPage";
    }

    @RequestMapping(value = "/addMusic", method = RequestMethod.POST)
    public String addMusic(Music music, AddMusicForm addMusicForm) {
        System.out.println("Adding music: " + addMusicForm.getTitle());

        Music newMusic = new Music(addMusicForm.getTitle(), addMusicForm.getComposer(),
                addMusicForm.getDuration(), addMusicForm.getType());

        dao.addMusic(newMusic);

        return "redirect:/musics";
    }

    @RequestMapping(value = "/deleteMusic", method = RequestMethod.GET)
    public String showDeleteMusicForm(Model model) {
        return "deleteMusicByTitlePage";
    }

    @RequestMapping(value = "/deleteMusic", method = RequestMethod.POST)
    public String deleteMusic(@ModelAttribute DeleteMusicForm deleteMusicForm, Model model) {
        String title = deleteMusicForm.getTitle();
        System.out.println("Deleting music with title: " + title);

        dao.deleteMusic(title);

        model.addAttribute("message", "Music with title '" + title + "' has been deleted.");
        return "redirect:/musics";
    }

    @RequestMapping(value = "/getMusicByComposer", method = RequestMethod.GET)
    public String showMusicByComposerForm(Model model) {
        model.addAttribute("getMusicByComposerForm", new GetMusicByComposerForm());
        return "getMusicByComposer";
    }


    @RequestMapping(value = "/getMusicByComposer", method = RequestMethod.POST)
    public String getMusicByComposer(@ModelAttribute GetMusicByComposerForm form, Model model) {
        List<Music> musics = dao.findMusicByComposer(form.getComposer());

        if (!musics.isEmpty()) {
            model.addAttribute("musics", musics);
        } else {
            model.addAttribute("message", "Немає музики від композитора: " + form.getComposer());
        }
        return "getMusicByComposer";
    }

    @RequestMapping(value = "/updateMusic", method = RequestMethod.GET)
    public String showUpdateMusicForm(Model model) {
        List<Music> musics = dao.getAllMusics();
        model.addAttribute("allMusics", musics);
        model.addAttribute("updateMusicForm", new EditMusicForm());
        return "UpdateMusicPage";
    }

    @RequestMapping(value = "/updateMusic", method = RequestMethod.POST)
    public String updateMusic(@ModelAttribute EditMusicForm updateMusicForm, Model model) {
        int id = updateMusicForm.getId();
        Music updatedMusic = new Music(updateMusicForm.getTitle(), updateMusicForm.getComposer(),
                updateMusicForm.getDuration(), updateMusicForm.getType());

        dao.updateMusic(id, updatedMusic);

        return "redirect:/musics";
    }
}
